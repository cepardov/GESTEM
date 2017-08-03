package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_SUPERADMIN')
@Transactional(readOnly = true)
class CorreoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def idUser

    def index(Integer max,Correo correo) {
        def correoByUser = Correo.findAllByUser(User.findById(params.idUser))
        def correos = Correo.list(params)

        idUser = params.idUser

        params.max = Math.min(max ?: 10, 100)

        if(params.id!=null){
            if(params.idUser!=null){
                respond correo, model:[correoCount: Correo.countByUser(User.findById(params.idUser)), correoList: correoByUser]
            } else {
                respond correo, model:[correoCount: Correo.count(), correoList:correos]
            }
        } else if(params.idUser!=null){
            respond new Correo(params), model:[correoCount: Correo.countByUser(User.findById(params.idUser)), correoList: correoByUser]
        } else {
            respond new Correo(params), model:[correoCount: Correo.count(), correoList:correos]
        }

    }

    def show(Correo correo) {
        respond correo
    }

    def create() {
        respond new Correo(params)
    }

    @Transactional
    def save(Correo correo) {
        if (correo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (correo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond correo.errors, view:'create'
            return
        }

        correo.save flush:true

        printf('\nReturn:'+params.r)
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'correo.label', default: 'Correo'), correo.id])

                if(params.r != "showUser"){
                    redirect (controller: "correo", action: "index")
                } else {
                    redirect(controller: "user", action: "show", id: params.idUser, params: [name : params.name, lastName : params.lastName])
                }
            }
            '*' { respond correo, [status: CREATED] }
        }
    }

    def edit(Correo correo) {
        respond correo
    }

    def eliminar(){
        def correo = Correo.get(params.id)
        correo.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'correo.label', default: 'Correo'), correo.id])
        if(params.r != "showUser"){
            redirect (controller: "correo", action: "index")
        } else {
            redirect(controller: "user", action: "show", id: params.idUser, params: [name : params.name, lastName : params.lastName])
        }
    }

    @Transactional
    def update(Correo correo) {
        if (correo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (correo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond correo.errors, view:'edit'
            return
        }

        correo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'correo.label', default: 'Correo'), correo.id])
                if(params.r != "showUser"){
                    redirect (controller: "correo", action: "index")
                } else {
                    redirect(controller: "user", action: "show", id: params.idUser, params: [name : params.name, lastName : params.lastName])
                }
            }
            '*'{ respond correo, [status: OK] }
        }
    }

    def delete(){
        eliminar()
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'correo.label', default: 'Correo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
