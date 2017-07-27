package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CorreoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Correo.list(params), model:[correoCount: Correo.count()]
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

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'correo.label', default: 'Correo'), correo.id])
                redirect correo
            }
            '*' { respond correo, [status: CREATED] }
        }
    }

    def edit(Correo correo) {
        respond correo
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
                redirect correo
            }
            '*'{ respond correo, [status: OK] }
        }
    }

    @Transactional
    def delete(Correo correo) {

        if (correo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        correo.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'correo.label', default: 'Correo'), correo.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
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
