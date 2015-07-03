package br.com.ufes.dwws.socialMusic

import org.openrdf.model.BNode
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


import org.openrdf.model.Model;
import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.openrdf.model.util.Literals;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.model.vocabulary.XMLSchema;
import org.openrdf.model.vocabulary.FOAF
import org.openrdf.rio.Rio;


class RDFController {

    final Map<String, String>  musicBrainzBase = [
            URI: 'http://purl.org/ontology/mo/',
            TRACK: 'Track'
    ]

    def afterInterceptor = {
        renderRDF()
    }

    static final String musicBrainzBaseURI = 'http://purl.org/ontology/mo/'

    private Model modelInstance

    private void renderRDF(RDFFormat formato = RDFFormat.RDFXML) throws RDFHandlerException, IOException  {
        Rio.write(model, response.outputStream, formato);
    }

    private ValueFactory getFactory() {
        ValueFactoryImpl.instance
    }

    private  Model getModel() {
        if (!modelInstance)
            modelInstance = new LinkedHashModel()

        modelInstance
    }

    private void buildMusicRDF(Music music) {

        model.setNamespace("rdf", RDF.NAMESPACE);
        model.setNamespace("rdfs", RDFS.NAMESPACE);
        model.setNamespace("xsd", XMLSchema.NAMESPACE);
        model.setNamespace("mo", musicBrainzBaseURI);
        model.setNamespace('foaf', FOAF.NAMESPACE)


        URI moRecording = factory.createURI(musicBrainzBaseURI, 'Recording')
        URI moRelease = factory.createURI(musicBrainzBaseURI, 'Release')
        URI moMedium = factory.createURI(musicBrainzBaseURI, 'Medium')



        URI uriMusicResource =  factory.createURI(createLink(controller: 'music', action: 'show', id: music.id, absolute: true) as String)

        //Literal, indicando qual o valor da propriedade recurso musica, no caso o nome dela
        Literal musicLiteral = factory.createLiteral(music.name)

        model.add(uriMusicResource, FOAF.NAME, musicLiteral)
        model.add(uriMusicResource, RDF.TYPE, moRecording)
        Literal linkYouTubeLiteral = factory.createLiteral(music.url)
        model.add(uriMusicResource, moMedium,linkYouTubeLiteral)

        URI albumEntity = factory.createURI(createLink(controller: 'album', action: 'show', id: music.album.id, absolute: true) as String)

        model.add(albumEntity, moRelease, uriMusicResource)
    }


    private void buildAuthorshipRDF(Authorship authorship) {
        model.setNamespace("rdf", RDF.NAMESPACE);
        model.setNamespace("rdfs", RDFS.NAMESPACE);
        model.setNamespace("xsd", XMLSchema.NAMESPACE);
        model.setNamespace("mo", musicBrainzBaseURI);
        model.setNamespace('foaf', FOAF.NAMESPACE)


    //TODO encontrar vocabulario para authorship
        URI moauthorship = factory.createURI(musicBrainzBaseURI, 'authorship')

        URI authorshipEntity = factory.createURI(createLink(controller: 'authorship', action: 'show', id: authorship.id, absolute: true) as String)
        Literal authorshipNameLiteral = factory.createLiteral(authorship.name)
        model.add(authorshipEntity, FOAF.NAME, authorshipNameLiteral)
        model.add(authorshipEntity, RDF.TYPE, moauthorship)

        Literal linkLiteral = factory.createLiteral(authorship.page)
        model.add(authorshipEntity, FOAF.PAGE, linkLiteral)
    }

    private void buildAlbum(Album album) {
        model.setNamespace("rdf", RDF.NAMESPACE);
        model.setNamespace("rdfs", RDFS.NAMESPACE);
        model.setNamespace("xsd", XMLSchema.NAMESPACE);
        model.setNamespace("mo", musicBrainzBaseURI);
        model.setNamespace('foaf', FOAF.NAMESPACE)



        URI moAlbum = factory.createURI(musicBrainzBaseURI, 'album')

        URI albumEntity = factory.createURI(createLink(controller: 'album', action: 'show', id: album.id, absolute: true) as String)
        Literal albumNameLiteral = factory.createLiteral(album.name)
        model.add(albumEntity, FOAF.NAME, albumNameLiteral)
        model.add(albumEntity, RDF.TYPE, moAlbum)

        Literal linkLiteral = factory.createLiteral(album.page)
        model.add(albumEntity, FOAF.PAGE, linkLiteral)

    }



    def authorships() {
        Authorship.list().each {Authorship authorship ->
            buildAuthorshipRDF(authorship)
        }
    }

    def authorship(Authorship authorshipInstance) {
        buildAuthorshipRDF(authorshipInstance)
    }

    def albuns() {
        Album.list().each { Album album ->
            buildAlbum(album)
        }
    }

    def album(Album albumInstance) {
        buildAlbum(albumInstance)
    }


    def musics() {
        Music.list().each { Music music ->
            buildMusicRDF(music)
        }
    }


    def music(Music musicInstance) {
       buildMusicRDF(musicInstance)
    }
}
