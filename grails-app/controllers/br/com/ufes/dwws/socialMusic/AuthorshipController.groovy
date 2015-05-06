package br.com.ufes.dwws.socialMusic



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AuthorshipController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Authorship.list(params), model:[authorshipInstanceCount: Authorship.count()]
    }

    def show(Authorship authorshipInstance) {
        respond authorshipInstance
    }

    def create() {
        respond new Authorship(params)
    }

    @Transactional
    def save(Authorship authorshipInstance) {
        if (authorshipInstance == null) {
            notFound()
            return
        }

        if (authorshipInstance.hasErrors()) {
            respond authorshipInstance.errors, view:'create'
            return
        }

        authorshipInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'authorshipInstance.label', default: 'Authorship'), authorshipInstance.id])
                redirect authorshipInstance
            }
            '*' { respond authorshipInstance, [status: CREATED] }
        }
    }

    def edit(Authorship authorshipInstance) {
        respond authorshipInstance
    }

    @Transactional
    def update(Authorship authorshipInstance) {
        if (authorshipInstance == null) {
            notFound()
            return
        }

        if (authorshipInstance.hasErrors()) {
            respond authorshipInstance.errors, view:'edit'
            return
        }

        authorshipInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Authorship.label', default: 'Authorship'), authorshipInstance.id])
                redirect authorshipInstance
            }
            '*'{ respond authorshipInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Authorship authorshipInstance) {

        if (authorshipInstance == null) {
            notFound()
            return
        }

        authorshipInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Authorship.label', default: 'Authorship'), authorshipInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'authorshipInstance.label', default: 'Authorship'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
