package br.com.ufes.dwws.socialMusic

import grails.converters.JSON
import org.openrdf.model.vocabulary.SESAME
import org.springframework.context.i18n.LocaleContextHolder

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AlbumController {

    RdfService rdfService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Album.list(params), model:[albumInstanceCount: Album.count()]
    }

    def show(Album albumInstance) {
        Map<String, Object> rdfAlbumDataFormatado = buildAlbumData(albumInstance)
        respond albumInstance, model: [rdfAlbumData: rdfAlbumDataFormatado]
    }

    def create() {
        respond new Album(params)
    }

    @Transactional
    def save(Album albumInstance) {
        if (albumInstance == null) {
            notFound()
            return
        }

        if (albumInstance.hasErrors()) {
            respond albumInstance.errors, view:'create'
            return
        }

        albumInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'albumInstance.label', default: 'Album'), albumInstance.id])
                redirect albumInstance
            }
            '*' { respond albumInstance, [status: CREATED] }
        }
    }

    def edit(Album albumInstance) {
        respond albumInstance
    }

    @Transactional
    def update(Album albumInstance) {
        if (albumInstance == null) {
            notFound()
            return
        }

        if (albumInstance.hasErrors()) {
            respond albumInstance.errors, view:'edit'
            return
        }

        albumInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Album.label', default: 'Album'), albumInstance.id])
                redirect albumInstance
            }
            '*'{ respond albumInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Album albumInstance) {

        if (albumInstance == null) {
            notFound()
            return
        }

        albumInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Album.label', default: 'Album'), albumInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'albumInstance.label', default: 'Album'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def lastAdded() {

        Album album = Album.last()
        
        render "{\"id\":\"${album.id}\", \"name\":\"${album.name}\",\"page\":\"${album.page}\"}"
    }

    def loadAlbumNames() {
        String name = params.term
        Long authorshipId = params.authorshipId as Long
        Authorship authorship = Authorship.get(authorshipId)
        List<Map<String, Object>> rdfNamesMusic = rdfService.getAlbumAutocomplete(name, authorship.name)
        List<String> names
        if (rdfNamesMusic) {
            names = rdfNamesMusic.collect { (it.name as String).replace('\"', '') }
        } else {
            names = []
        }

        render names as JSON

        return  names
    }


   private Map<String, Object> buildAlbumData(Album albumInstance) {
       String lang = LocaleContextHolder.getLocale().language
       List<Map<String, Object>> rdfAlbumData = rdfService.getAlbumData(albumInstance.name, albumInstance.authorship.name, lang)
       Map<String, Object> rdfAlbumDataFormatado = null
       if (rdfAlbumData) {
           rdfAlbumDataFormatado = rdfAlbumData ? rdfAlbumData.first() : null
           rdfAlbumDataFormatado.each { String chave, Object valor ->
               if (valor)
                rdfAlbumDataFormatado[chave] = valor.toString().replace('\"', '')
           }
       }

       return rdfAlbumDataFormatado
   }
}
