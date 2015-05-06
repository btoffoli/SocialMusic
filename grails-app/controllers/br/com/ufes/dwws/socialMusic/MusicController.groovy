package br.com.ufes.dwws.socialMusic



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MusicController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Music.list(params), model:[musicInstanceCount: Music.count()]
    }

    def show(Music musicInstance) {
        respond musicInstance
    }

    def create() {
        respond new Music(params)
    }

    @Transactional
    def save(Music musicInstance) {
        if (musicInstance == null) {
            notFound()
            return
        }

        if (musicInstance.hasErrors()) {
            respond musicInstance.errors, view:'create'
            return
        }

        musicInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'musicInstance.label', default: 'Music'), musicInstance.id])
                redirect musicInstance
            }
            '*' { respond musicInstance, [status: CREATED] }
        }
    }

    def edit(Music musicInstance) {
        respond musicInstance
    }

    @Transactional
    def update(Music musicInstance) {
        if (musicInstance == null) {
            notFound()
            return
        }

        if (musicInstance.hasErrors()) {
            respond musicInstance.errors, view:'edit'
            return
        }

        musicInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Music.label', default: 'Music'), musicInstance.id])
                redirect musicInstance
            }
            '*'{ respond musicInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Music musicInstance) {

        if (musicInstance == null) {
            notFound()
            return
        }

        musicInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Music.label', default: 'Music'), musicInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'musicInstance.label', default: 'Music'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
