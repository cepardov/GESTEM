package gestem

import grails.test.mixin.*
import spock.lang.*

@TestFor(SostenedorController)
@Mock(Sostenedor)
class SostenedorControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.sostenedorList
            model.sostenedorCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.sostenedor!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def sostenedor = new Sostenedor()
            sostenedor.validate()
            controller.save(sostenedor)

        then:"The create view is rendered again with the correct model"
            model.sostenedor!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            sostenedor = new Sostenedor(params)

            controller.save(sostenedor)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/sostenedor/show/1'
            controller.flash.message != null
            Sostenedor.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def sostenedor = new Sostenedor(params)
            controller.show(sostenedor)

        then:"A model is populated containing the domain instance"
            model.sostenedor == sostenedor
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def sostenedor = new Sostenedor(params)
            controller.edit(sostenedor)

        then:"A model is populated containing the domain instance"
            model.sostenedor == sostenedor
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/sostenedor/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def sostenedor = new Sostenedor()
            sostenedor.validate()
            controller.update(sostenedor)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.sostenedor == sostenedor

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            sostenedor = new Sostenedor(params).save(flush: true)
            controller.update(sostenedor)

        then:"A redirect is issued to the show action"
            sostenedor != null
            response.redirectedUrl == "/sostenedor/show/$sostenedor.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/sostenedor/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def sostenedor = new Sostenedor(params).save(flush: true)

        then:"It exists"
            Sostenedor.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(sostenedor)

        then:"The instance is deleted"
            Sostenedor.count() == 0
            response.redirectedUrl == '/sostenedor/index'
            flash.message != null
    }
}
