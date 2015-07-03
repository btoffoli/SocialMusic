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

    static final String dbPedia = 'http://dbpedia.org/sparql'

    def test1() {

        String queryStr = '''
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

            SELECT ?node, ?name, ?given
            WHERE {
            ?node foaf:name ?name .
            OPTIONAL {
             ?node foaf:givenName ?given .
            }
            } LIMIT 1000
        '''

        //Repository repo = new SPARQLRepository(dbPedia, '')
        Repository repo = new HTTPRepository(dbPedia, '')
        //Repository repo = new TupleRepo Repository(dbPedia, '')

        repo.initialize()
        RepositoryConnection repoConnection = repo.connection

//        SPARQLTupleQuery query = repoConnection.prepareQuery(QueryLanguage.SPARQL, queryStr)
        TupleQuery query = repoConnection.prepareQuery(QueryLanguage.SPARQL, queryStr)

        QueryResult result = query.evaluate()

//        for(BindingSet bindSet : result) {
//            String name = bindSet.getValue('name')
//            println name
//            for (String binding : bindSet.getBindingNames()) {
//                println binding
//            }
//        }
        List<Map<String, Object>> resp = []
        while (result.hasNext()) {
            BindingSet bindSet = result.next();
            String node = bindSet.getValue('node')
            String name = bindSet.getValue('name')
            String given = bindSet.getValue('give')
            resp << [
                    node: node,
                    name: name,
                    given: given
            ]
        }

        return resp

    }


}
