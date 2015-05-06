package br.com.ufes.dwws.socialMusic



import grails.test.mixin.*
import spock.lang.*

@TestFor(AuthorshipController)
@Mock(Authorship)
class AuthorshipControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.authorshipInstanceList
            model.authorshipInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.authorshipInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def authorship = new Authorship()
            authorship.validate()
            controller.save(authorship)

        then:"The create view is rendered again with the correct model"
            model.authorshipInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            authorship = new Authorship(params)

            controller.save(authorship)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/authorship/show/1'
            controller.flash.message != null
            Authorship.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def authorship = new Authorship(params)
            controller.show(authorship)

        then:"A model is populated containing the domain instance"
            model.authorshipInstance == authorship
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def authorship = new Authorship(params)
            controller.edit(authorship)

        then:"A model is populated containing the domain instance"
            model.authorshipInstance == authorship
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            status == 404

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def authorship = new Authorship()
            authorship.validate()
            controller.update(authorship)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.authorshipInstance == authorship

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            authorship = new Authorship(params).save(flush: true)
            controller.update(authorship)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/authorship/show/$authorship.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            status == 404

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def authorship = new Authorship(params).save(flush: true)

        then:"It exists"
            Authorship.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(authorship)

        then:"The instance is deleted"
            Authorship.count() == 0
            response.redirectedUrl == '/authorship/index'
            flash.message != null
    }
}
