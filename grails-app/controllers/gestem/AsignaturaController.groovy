package gestem

import static org.springframework.http.HttpStatus.*
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_LEVEL0','ROLE_LEVELX'])
@Transactional(readOnly = true)
class AsignaturaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "POST"]

    def index(Integer max,Asignatura asignatura) {
        def asignaturas = Asignatura.list(params)
        def educacion = Educacion.list()
        params.max = Math.min(max ?: 10, 100)
        if(params.id!=null){
            respond asignatura, model:[asignaturaCount: Asignatura.count(), asignaturaList:asignaturas, educacionList:educacion, educacionCount: Educacion.count()]
        }else{
            respond new Asignatura(params), model:[asignaturaCount: Asignatura.count(), asignaturaList:asignaturas, educacionList:educacion, educacionCount: Educacion.count()]
        }
    }

    def show(Asignatura asignatura) {
        respond(asignatura)
    }

    def create() {
        respond new Asignatura(params)
    }

    @Transactional
    def save(Asignatura asignatura) {
        if (asignatura == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (asignatura.hasErrors()) {
            transactionStatus.setRollbackOnly()
            //asignatura.errors.allErrors.each {
            //    flash.message = message(it.defaultMessage)
            //}
            //redirect(controller:"asignatura", action: "index")
            respond asignatura.errors, view:'index'
            return
        }

        asignatura.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'asignatura.label', default: 'Asignatura'), asignatura.id, asignatura.code, asignatura.name, ''])
                redirect(controller:"asignatura", action: "index")
            }
            '*' { respond asignatura, [status: CREATED] }
        }
    }

    def edit(Asignatura asignatura) {
        respond asignatura
    }

    def eliminar(){
        def asignatura = Asignatura.get(params.id)

        if(Region.countByAsignatura(asignatura)>0){
            flash.message = "No tiene suficientes privilegios para eliminar en cascada"
        } else {
            asignatura.delete(flush:true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'asignatura.label', default: 'Asignatura'), asignatura.id, asignatura.code, asignatura.name, ''])
        }
        redirect (controller: "asignatura", action: "index")
    }

    @Transactional
    def update(Asignatura asignatura) {
        if (asignatura == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (asignatura.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond asignatura.errors, view:'index'
            return
        }

        asignatura.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'asignatura.label', default: 'Asignatura'), asignatura.id, asignatura.code, asignatura.name, ''])
                redirect(controller:"asignatura", action: "index")
            }
            '*'{ respond asignatura, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'asignatura.label', default: 'Asignatura'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
