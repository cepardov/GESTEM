package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TelefonoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def idUsuario

    def index(Integer max,Telefono telefono) {
        def telefonoByUsuario = Telefono.findAllByUsuario(Usuario.findById(params.idUsuario))
        def telefonos = Telefono.list(params)

        idUsuario = params.idUsuario

        params.max = Math.min(max ?: 10, 100)

        if(params.id!=null){
            if(params.idUsuario!=null){
                respond telefono, model:[telefonoCount: Telefono.countByUsuario(Usuario.findById(params.idUsuario)), telefonoList: telefonoByUsuario]
            } else {
                respond telefono, model:[telefonoCount: Telefono.count(), telefonoList:telefonos]
            }
        } else if(params.idUsuario!=null){
            respond new Telefono(params), model:[telefonoCount: Telefono.countByUsuario(Usuario.findById(params.idUsuario)), telefonoList: telefonoByUsuario]
        } else {
            respond new Telefono(params), model:[telefonoCount: Telefono.count(), telefonoList:telefonos]
        }

    }

    def show(Telefono telefono) {
        respond telefono
    }

    def create() {
        respond new Telefono(params)
    }

    @Transactional
    def save(Telefono telefono) {
        if (telefono == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (telefono.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond telefono.errors, view:'create'
            return
        }

        telefono.save flush:true

        printf('\nReturn:'+params.r)
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'telefono.label', default: 'Telefono'), telefono.id])

                if(params.r != "showUsuario"){
                    redirect (controller: "telefono", action: "index")
                } else {
                    redirect(controller: "usuario", action: "show", id: params.idUsuario, params: [name : params.name, lastName : params.lastName])
                }
            }
            '*' { respond telefono, [status: CREATED] }
        }
    }

    def edit(Telefono telefono) {
        respond telefono
    }

    def eliminar(){
        def telefono = Telefono.get(params.id)
        telefono.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'telefono.label', default: 'Telefono'), telefono.id])
        if(params.r != "showUsuario"){
            redirect (controller: "telefono", action: "index")
        } else {
            redirect(controller: "usuario", action: "show", id: params.idUsuario, params: [name : params.name, lastName : params.lastName])
        }
    }

    @Transactional
    def update(Telefono telefono) {
        if (telefono == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (telefono.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond telefono.errors, view:'edit'
            return
        }

        telefono.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'telefono.label', default: 'Telefono'), telefono.id])
                if(params.r != "showUsuario"){
                    redirect (controller: "telefono", action: "index")
                } else {
                    redirect(controller: "usuario", action: "show", id: params.idUsuario, params: [name : params.name, lastName : params.lastName])
                }
            }
            '*'{ respond telefono, [status: OK] }
        }
    }

    def delete(){
        eliminar()
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'telefono.label', default: 'Telefono'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
