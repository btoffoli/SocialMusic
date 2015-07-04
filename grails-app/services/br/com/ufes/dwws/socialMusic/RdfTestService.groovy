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
class RdfTestService {

    static final String dbpedia = 'http://dbpedia.org/sparql'
    static final String musicbrainz = 'http://linkedbrainz.org/sparql'

    def executeQuery(endpoint, queryString, params) {

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

        return resp
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

        def result = executeQuery(musicbrainz, queryStr, ['mbProducer', 'dpProducer'])
        return result
    }

    def test() {

        return getAuthorship('Red Hot Chili Peppers')

    }

}
