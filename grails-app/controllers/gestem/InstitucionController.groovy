package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class InstitucionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Institucion.list(params), model:[institucionCount: Institucion.count()]
    }

    def show(Institucion institucion) {
        respond institucion
    }

    def create() {
        respond new Institucion(params)
    }

    @Transactional
    def save(Institucion institucion) {
        if (institucion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (institucion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond institucion.errors, view:'create'
            return
        }

        institucion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'institucion.label', default: 'Institucion'), institucion.id])
                redirect institucion
            }
            '*' { respond institucion, [status: CREATED] }
        }
    }

    def edit(Institucion institucion) {
        respond institucion
    }

    @Transactional
    def update(Institucion institucion) {
        if (institucion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (institucion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond institucion.errors, view:'edit'
            return
        }

        institucion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'institucion.label', default: 'Institucion'), institucion.id])
                redirect institucion
            }
            '*'{ respond institucion, [status: OK] }
        }
    }

    @Transactional
    def delete(Institucion institucion) {

        if (institucion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        institucion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'institucion.label', default: 'Institucion'), institucion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'institucion.label', default: 'Institucion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
