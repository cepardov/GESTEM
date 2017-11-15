package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_LEVEL0','ROLE_LEVELX'])
@Transactional(readOnly = true)
class EducacionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "POST"]

    def index(Integer max,Educacion educacion) {
        def educacions = Educacion.list(params)
        params.max = Math.min(max ?: 10, 100)
        if(params.id!=null){
            respond educacion, model:[educacionCount: Educacion.count(), educacionList:educacions]
        }else{
            respond new Educacion(params), model:[educacionCount: Educacion.count(), educacionList:educacions]
        }
    }

    def show(Educacion educacion) {
        respond(educacion)
    }

    def create() {
        respond new Educacion(params)
    }

    @Transactional
    def save(Educacion educacion) {
        if (educacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (educacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond educacion.errors, view:'create'
            return
        }

        educacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'educacion.label', default: 'Educacion'), educacion.id, educacion.code, educacion.name, ''])
                redirect(controller:"educacion", action: "index")
            }
            '*' { respond educacion, [status: CREATED] }
        }
    }

    def edit(Educacion educacion) {
        respond educacion
    }

    def eliminar(){
        def educacion = Educacion.get(params.id)

        if(Region.countByEducacion(educacion)>0){
            flash.message = "No tiene suficientes privilegios para eliminar en cascada"
        } else {
            educacion.delete(flush:true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'educacion.label', default: 'Educacion'), educacion.id, educacion.code, educacion.name, ''])
        }
        redirect (controller: "educacion", action: "index")
    }

    @Transactional
    def update(Educacion educacion) {
        if (educacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (educacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond educacion.errors, view:'edit'
            return
        }

        educacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'educacion.label', default: 'Educacion'), educacion.id, educacion.code, educacion.name, ''])
                redirect(controller:"educacion", action: "index")
            }
            '*'{ respond educacion, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'educacion.label', default: 'Educacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
