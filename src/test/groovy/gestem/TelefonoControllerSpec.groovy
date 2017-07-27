package gestem

import grails.test.mixin.*
import spock.lang.*

@TestFor(TelefonoController)
@Mock(Telefono)
class TelefonoControllerSpec extends Specification {

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
            !model.telefonoList
            model.telefonoCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.telefono!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def telefono = new Telefono()
            telefono.validate()
            controller.save(telefono)

        then:"The create view is rendered again with the correct model"
            model.telefono!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            telefono = new Telefono(params)

            controller.save(telefono)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/telefono/show/1'
            controller.flash.message != null
            Telefono.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def telefono = new Telefono(params)
            controller.show(telefono)

        then:"A model is populated containing the domain instance"
            model.telefono == telefono
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def telefono = new Telefono(params)
            controller.edit(telefono)

        then:"A model is populated containing the domain instance"
            model.telefono == telefono
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/telefono/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def telefono = new Telefono()
            telefono.validate()
            controller.update(telefono)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.telefono == telefono

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            telefono = new Telefono(params).save(flush: true)
            controller.update(telefono)

        then:"A redirect is issued to the show action"
            telefono != null
            response.redirectedUrl == "/telefono/show/$telefono.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/telefono/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def telefono = new Telefono(params).save(flush: true)

        then:"It exists"
            Telefono.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(telefono)

        then:"The instance is deleted"
            Telefono.count() == 0
            response.redirectedUrl == '/telefono/index'
            flash.message != null
    }
}
