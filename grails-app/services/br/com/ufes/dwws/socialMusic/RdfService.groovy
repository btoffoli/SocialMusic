package br.com.ufes.dwws.socialMusic

import grails.transaction.Transactional
import org.openrdf.model.Literal
import org.openrdf.model.Model
import org.openrdf.model.Resource
import org.openrdf.model.Statement
import org.openrdf.model.ValueFactory
import org.openrdf.model.impl.NamespaceImpl
import org.openrdf.model.impl.ValueFactoryImpl
import org.openrdf.query.BindingSet
import org.openrdf.query.Query
import org.openrdf.query.QueryLanguage
import org.openrdf.query.QueryResult
import org.openrdf.query.TupleQuery
import org.openrdf.query.TupleQueryResult
import org.openrdf.repository.Repository
import org.openrdf.repository.RepositoryConnection
import org.openrdf.repository.base.RepositoryWrapper
import org.openrdf.repository.http.HTTPRepository
import org.openrdf.repository.http.HTTPTupleQuery
import org.openrdf.repository.sparql.SPARQLRepository
import org.openrdf.repository.sparql.query.SPARQLTupleQuery
import org.springframework.web.method.annotation.ModelFactory

@Transactional
class RdfService {

    static final String dbpedia = 'http://dbpedia.org/sparql'
    static final String musicbrainz = 'http://linkedbrainz.org/sparql'

    def executeQuery(endpoint, queryString, params) {
        try {
            Repository repo = new HTTPRepository(endpoint, '')
            repo.initialize()

            RepositoryConnection repoConnection = repo.connection

            TupleQuery query = repoConnection.prepareQuery(QueryLanguage.SPARQL, queryString)
            QueryResult result = query.evaluate()

            List<Map<String, Object>> resp = []
            while (result.hasNext()) {
                BindingSet bindSet = result.next();

                Map<String, Object> bindValues = [:]
                params.each {
                    bindValues.put(it, bindSet.getValue(it))
                }

                resp << bindValues
            }

            repo.shutDown()

            return resp
        } catch (e) {
            log.error("Erro ao consultar o endpoint - $endpoint", e)
        }

        return []
    }

    def getAuthorship(name) {

        name = name.toLowerCase()

        String queryStr = """
            PREFIX mo: <http://purl.org/ontology/mo/>
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            prefix owl: <http://www.w3.org/2002/07/owl#>

            SELECT ?mbProducer ?dpProducer
            WHERE {
              ?mbProducer owl:sameAs ?dpProducer .

              ?mbProducer rdf:type mo:MusicGroup .
              ?mbProducer foaf:name ?name .

                FILTER (regex(lcase(str(?name)), "${name}"))
            }
        """

        return executeQuery(musicbrainz, queryStr, ['mbProducer', 'dpProducer'])
    }

    def getAuthorshipAutocomplete(name) {

        name = name.toLowerCase()

        String queryStr = """
            PREFIX mo: <http://purl.org/ontology/mo/>
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            prefix owl: <http://www.w3.org/2002/07/owl#>

            SELECT DISTINCT str(?name) AS ?name
            WHERE {
              ?mbProducer owl:sameAs ?dpProducer .

              ?mbProducer rdf:type mo:MusicGroup .
              ?mbProducer foaf:name ?name .

                FILTER (regex(lcase(str(?name)), "${name}"))
            }
        """

        return executeQuery(musicbrainz, queryStr, ['name'])
    }

    def getAuthorshipData(name, language) {

        def authorship = getAuthorship(name)
        if (!authorship)
            return []

        authorship = authorship[0].'dpProducer'

        String queryStr = """
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>

            PREFIX resource: <${authorship}>

            SELECT str(?name) AS ?name
                   ?webpage
                   ?image
                   str(?releaseYear) AS ?releaseYear
                   str(?abstract) AS ?abstract
            WHERE {
              resource: foaf:name ?name .
              resource: foaf:homepage ?webpage .
              resource: foaf:depiction ?image .
              resource: dbpedia-owl:activeYearsStartYear ?releaseYear .
              resource: dbpedia-owl:abstract ?abstract .

            FILTER (langMatches(lang(?abstract), "${language}"))
            }
        """

        return executeQuery(dbpedia, queryStr, ['name', 'webpage', 'image', 'releaseYear', 'abstract'])
    }

    def getAuthorshipMembers(name) {

        def authorship = getAuthorship(name)
        if (!authorship)
            return []

        def dpAuthorship = authorship[0].'dpProducer'
        def mbAuthorship = authorship[0].'mbProducer'

        String queryStr = """
            PREFIX mo: <http://purl.org/ontology/mo/>
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            PREFIX owl: <http://www.w3.org/2002/07/owl#>

            PREFIX resource: <${mbAuthorship}>

            SELECT ?dpEntity
            WHERE {
              ?member owl:sameAs ?dpEntity .

              ?member mo:member_of resource: .
            }
        """

        def dpMembers = executeQuery(musicbrainz, queryStr, ['dpEntity'])

        List<Map<String, Object>> dpMembersData = []
        def dpMember
        dpMembers.each {
            dpMember = it.'dpEntity'

            queryStr = """
                PREFIX foaf: <http://xmlns.com/foaf/0.1/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>

                PREFIX resource: <${dpMember}>

                SELECT str(?name) AS ?name
                       ?image
                       str(?memberSince) AS ?memberSince
                WHERE {
                  resource: foaf:name ?name .
                  OPTIONAL {resource: foaf:depiction ?image}
                  resource: dbpedia-owl:activeYearsStartYear ?memberSince .
                }
            """

            def dpMemberData = executeQuery(dbpedia, queryStr, ['name', 'image', 'memberSince'])
            dpMemberData = dpMemberData[0]

            dpMembersData << dpMemberData
        }

        return dpMembersData
    }

    def getAlbum(name, authorshipName) {

        name = name.toLowerCase()

        def authorship = getAuthorship(authorshipName)
        if (!authorship)
            return []

        authorship = authorship[0].'dpProducer'

        String queryStr = """
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>
            PREFIX dbpprop: <http://dbpedia.org/property/>

            PREFIX resource: <${authorship}>

            SELECT ?album
            WHERE {
                ?album foaf:name ?name .
                ?album rdf:type dbpedia-owl:Album .
                ?album dbpedia-owl:artist resource: .

            FILTER (regex(lcase(str(?name)), "${name}"))
            }
        """

        return executeQuery(dbpedia, queryStr, ['album'])
    }

    def getAlbumAutocomplete(name, authorshipName) {

        name = name.toLowerCase()

        def authorship = getAuthorship(authorshipName)
        if (!authorship)
            return []

        authorship = authorship[0].'dpProducer'

        String queryStr = """
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>
            PREFIX dbpprop: <http://dbpedia.org/property/>

            PREFIX resource: <${authorship}>

            SELECT DISTINCT str(?name) AS ?name
            WHERE {
                ?album foaf:name ?name .
                ?album rdf:type dbpedia-owl:Album .
                ?album dbpedia-owl:artist resource: .

            FILTER (regex(lcase(str(?name)), "${name}"))
            }
        """

        return executeQuery(dbpedia, queryStr, ['name'])
    }

    def getAlbumData(name, authorshipName, language) {

        def album = getAlbum(name, authorshipName)
        if (!album)
            return []

        album = album[0].'album'

        String queryStr = """
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>
            PREFIX dbpprop: <http://dbpedia.org/property/>

            PREFIX resource: <${album}>

            SELECT str(?name) AS ?name
                   (CONCAT('https://en.wikipedia.org/wiki/File:', ?image) AS ?image)
                   str(?release) AS ?release
                   (GROUP_CONCAT(?awards;separator=", ") as ?awards)
                   str(?abstract) AS ?abstract
            WHERE {
              resource: foaf:name ?name .
              resource: dbpprop:cover ?image .
              resource: dbpprop:relyear ?release .
              resource: dbpprop:award ?awards .
              resource: dbpedia-owl:abstract ?abstract .

            FILTER (langMatches(lang(?abstract), "${language}"))
            }
        """

        return executeQuery(dbpedia, queryStr, ['name', 'image', 'release', 'awards', 'abstract'])
    }

    def getMusic(name, authorshipName) {

        name = name.toLowerCase()
        authorshipName = authorshipName.toLowerCase()

        String queryStr = """
            PREFIX mo: <http://purl.org/ontology/mo/>
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            PREFIX dc: <http://purl.org/dc/elements/1.1/>
            PREFIX event: <http://purl.org/NET/c4dm/event.owl#>

            SELECT ?track
            WHERE {
                ?track dc:title ?trackTitle .
                ?producer foaf:made ?track .
                ?producer foaf:name ?producerName .

            FILTER (regex(lcase(str(?producerName)), "${authorshipName}"))
            FILTER (regex(lcase(str(?trackTitle)), "${name}"))
            }
        """

        return executeQuery(musicbrainz, queryStr, ['track'])
    }

    def getMusicAutocomplete(name, authorshipName) {

        name = name.toLowerCase()
        authorshipName = authorshipName.toLowerCase()

        String queryStr = """
            PREFIX mo: <http://purl.org/ontology/mo/>
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            PREFIX dc: <http://purl.org/dc/elements/1.1/>
            PREFIX event: <http://purl.org/NET/c4dm/event.owl#>

            SELECT DISTINCT str(?trackTitle) AS ?trackTitle
            WHERE {
                ?track dc:title ?trackTitle .
                ?producer foaf:made ?track .
                ?producer foaf:name ?producerName .

            FILTER (regex(lcase(str(?producerName)), "${authorshipName}"))
            FILTER (regex(lcase(str(?trackTitle)), "${name}"))
            }
        """

        return executeQuery(musicbrainz, queryStr, ['trackTitle'])
    }

    def getMusicData(name, authorshipName) {

        name = name.toLowerCase()
        authorshipName = authorshipName.toLowerCase()

        String queryStr = """
            PREFIX mo: <http://purl.org/ontology/mo/>
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            PREFIX dc: <http://purl.org/dc/elements/1.1/>
            PREFIX event: <http://purl.org/NET/c4dm/event.owl#>


            SELECT str(?trackTitle) AS ?trackTitle
                   str(?trackDuration) AS ?trackDuration
            WHERE {
               ?track mo:duration ?trackDuration .
               ?track dc:title ?trackTitle .

               ?producer foaf:made ?track .
               ?producer foaf:name ?producerName .

            FILTER (regex(lcase(str(?producerName)), "${authorshipName}"))
            FILTER (regex(lcase(str(?trackTitle)), "${name}"))
            }
        """

        return executeQuery(musicbrainz, queryStr, ['trackTitle', 'trackDuration'])
    }

}
