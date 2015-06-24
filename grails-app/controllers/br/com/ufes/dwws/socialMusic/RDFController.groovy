package br.com.ufes.dwws.socialMusic

import org.openrdf.model.Literal
import org.openrdf.model.Model
import org.openrdf.model.Resource
import org.openrdf.model.Statement
import org.openrdf.model.ValueFactory
import org.openrdf.model.impl.NamespaceImpl
import org.openrdf.model.impl.ValueFactoryImpl
import org.openrdf.model.impl.LinkedHashModel
import org.openrdf.model.URI
import org.openrdf.rio.RDFFormat
import org.openrdf.rio.RDFHandlerException
import org.openrdf.rio.RDFWriter
import org.openrdf.rio.Rio
import org.springframework.web.method.annotation.ModelFactory

class RDFController {

    private renderRDF(Model modelo) throws RDFHandlerException, IOException  {
        OutputStream out = response.outputStream
        RDFWriter writer = Rio.createWriter(RDFFormat.RDFXML, out);
        writer.startRDF();
        for (Statement st: modelo) {
            writer.handleStatement(st);
        }
        writer.endRDF();
    }

    def index() { }
    
    def teste() {
        Model model = ModelFactory.createDefaultModel();
        String myNS = "http://localhost:8080/CDITravel/data/TourPackage/";
        String foafNS = "http://xmlns.com/foaf/0.1/";
        //model.setNsPrefix("gr", grNS);
        model.setNamespace(new NamespaceImpl('foaf', foafNS))
        ValueFactory factory = ValueFactoryImpl.instance
        Resource foafName = factory.creteURI(foafNS + 'name')
        Resource foafPerson = factory.creteURI(foafNS + 'Person')
        
        
        
        
        
//        Resource grOffering = ResourceFactory.createResource(grNS + "Offering");
//        Resource grPriceSpecification = ResourceFactory.createResource(grNS + "PriceSpecification");
//        Property gravailabilityStarts = ResourceFactory.createProperty(grNS + "availabilityStarts");
//        Property gravailabilityEnds = ResourceFactory.createProperty(grNS + "availabilityEnds");
//        Property grhasPriceSpecification = ResourceFactory.createProperty(grNS + "hasPriceSpecification");
//        Property grhasCurrencyValue = ResourceFactory.createProperty(grNS + "hasCurrencyValue");
    }
    
    
    def teste1() {
        ValueFactory factory = ValueFactoryImpl.getInstance();

        URI obama = factory.createURI("https://barackobama.com")
        String foafNS = "http://xmlns.com/foaf/0.1/"
        URI name = factory.createURI(foafNS + 'name')
        Literal obamasName = factory.createLiteral("Obama")
        Statement nameStatement = factory.createStatement(obama, name, obamasName)
        
        Model model = new LinkedHashModel()
        model.add(nameStatement)


        renderRDF(model)
    }


    def musics() {
        ValueFactory factory = ValueFactoryImpl.getInstance();

        String musicBrainzBaseURI = 'http://musicbrainz.org/mm/mm-2.1/'

        // URI indicando artista
        URI musicURI = factory.createURI(musicBrainzBaseURI, 'Track')

        Model model = new LinkedHashModel()

        Music.list().each { Music music ->
            //URI indicando o recurso artista
            //TODO verificar se esta correto ou se preciso encontrar o RDF do artista
            URI uriMusicResource =  factory.createURI(music.url)

            //Literal, indicando qual o valor da propriedade recurso musica, no caso o nome dela
            Literal musicLiteral = factory.createLiteral(music.name)

            Statement stmt = factory.createStatement(uriMusicResource, musicURI, musicLiteral)

            model.add(stmt)
        }

        renderRDF(model)
    }

    def artists() {

        ValueFactory factory = ValueFactoryImpl.getInstance();

        String musicBrainzBaseURI = 'http://musicbrainz.org/mm/mm-2.1/'

        // URI indicando artista
        URI artistURI = factory.createURI(musicBrainzBaseURI, 'Artist')

        Model model = new LinkedHashModel()

        Authorship.list().each { Authorship authorship ->
            //URI indicando o recurso artista
            //TODO verificar se esta correto ou se preciso encontrar o RDF do artista
            URI uriAuthorship =  factory.createURI(authorship.page)

            //Literal, indicando qual o valor da propriedade recurso artista, no caso o nome dele
            Literal authorshipLiteral = factory.createLiteral(authorship.name)

            Statement stmt = factory.createStatement(uriAuthorship, artistURI, authorshipLiteral)

            model.add(stmt)
        }

        renderRDF(model)
    }
}
