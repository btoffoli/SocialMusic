package br.com.ufes.dwws.socialMusic



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AccountController extends AbstractBaseController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Account.list(params), model:[accountInstanceCount: Account.count()]
    }

    def show(Account accountInstance) {
        respond accountInstance
    }

    def create() {
        respond new Account(params)
    }

    @Transactional
    def save(Account accountInstance) {
        if (accountInstance == null) {
            notFound()
            return
        }

        if (accountInstance.hasErrors()) {
            respond accountInstance.errors, view:'create'
            return
        }

        accountInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'accountInstance.label', default: 'Account'), accountInstance.id])
                redirect accountInstance
            }
            '*' { respond accountInstance, [status: CREATED] }
        }
    }

    def edit(Account accountInstance) {
        respond accountInstance
    }



    @Transactional
    def update(Account accountInstance) {
        if (accountInstance == null) {
            notFound()
            return
        }

        if (accountInstance.hasErrors()) {
            respond accountInstance.errors, view:'edit'
            return
        }

        accountInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Account.label', default: 'Account'), accountInstance.id])
                redirect accountInstance
            }
            '*'{ respond accountInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Account accountInstance) {

        if (accountInstance == null) {
            notFound()
            return
        }

        accountInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Account.label', default: 'Account'), accountInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'accountInstance.label', default: 'Account'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def newAccount() {
        respond new Account(params)
    }

    @Transactional
    def saveNewAccount(Account accountInstance) {
        if (accountInstance == null) {
            notFound()
            return
        }

        if (accountInstance.hasErrors()) {
            respond accountInstance.errors, view:'create'
            return
        }

        accountInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'accountInstance.label', default: 'Account'), accountInstance.id])
                redirect accountInstance
            }
            '*' { respond accountInstance, [status: CREATED] }
        }
    }

    def loginForm() {
        respond(accountInstance: new Account())
    }

    def signIn(Account accountInstance) {
        Account account = accountsManagerService.login(accountInstance)
        if (account) {
            session.objeto = new SessionObject(account: account)
            redirect(uri: '/')
        } else {
            flash.message = "Falha na autenticação"
            redirect(action: 'loginForm')
        }
    }

    def signOut() {
        session.invalidate()
        redirect(uri: '/')
    }
}
