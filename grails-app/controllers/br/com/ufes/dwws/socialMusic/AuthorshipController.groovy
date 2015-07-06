package br.com.ufes.dwws.socialMusic


import grails.converters.JSON
import groovy.json.*
import org.springframework.context.i18n.LocaleContextHolder

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AuthorshipController {

    RdfService rdfService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Authorship.list(params), model:[authorshipInstanceCount: Authorship.count()]
    }

    def show(Authorship authorshipInstance) {
        Map<String, Object> rdfAuthorshipData = buildAuthorshipData(authorshipInstance)
        List<Map> rdfMembers = buildMembersData(authorshipInstance)
        respond authorshipInstance, model: [rdfAuthorshipData: rdfAuthorshipData, rdfMembers: rdfMembers]
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

    def lastAdded() {

        Authorship authorship = Authorship.last()
        
        render "{\"id\":\"${authorship.id}\", \"name\":\"${authorship.name}\",\"page\":\"${authorship.page}\"}"
    }

    def loadAuthorshipNames() {
        String name = params.term
        List<Map<String, Object>> rdfNamesMusic = rdfService.getAuthorshipAutocomplete(name)
        List<String> names
        if (rdfNamesMusic) {
            names = rdfNamesMusic.collect { (it.name as String).replace('\"', '') }
        } else {
            names = []
        }

        render names as JSON

        return  names
    }

    private Map<String, Object> buildAuthorshipData(Authorship authorship) {
        Map<String, Object> resp = null
        String lang = LocaleContextHolder.getLocale().language
        List<Map<String, Object>> rdfAuthorShip = rdfService.getAuthorshipData(authorship.name, lang)

        if (rdfAuthorShip) {
            resp = rdfAuthorShip.first()
            resp.each { String chave, Object valor ->
                if (valor)
                    resp[chave] = valor.toString().replace('\"', '')
            }
        }

        return resp
    }

    private List<Map<String, Object>> buildMembersData(Authorship authorship) {
        List<Map<String, Object>> rdfMembers = rdfService.getAuthorshipMembers(authorship.name)

        if (rdfMembers) {
            rdfMembers.eachWithIndex { Map map, int idx ->
                map.each { String chave, Object valor ->
                    if (valor) {
                        //rdfMembers[idx][chave] =
                        map[chave] = valor.toString().replace('\"', '')
                    } else {
                        map[chave] = ''
                    }
                }
            }
        }

        return rdfMembers
    }
}
