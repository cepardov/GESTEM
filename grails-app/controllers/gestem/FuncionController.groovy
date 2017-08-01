package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FuncionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Funcion.list(params), model:[funcionCount: Funcion.count()]
    }

    def show(Funcion funcion) {
        respond funcion
    }

    def create() {
        respond new Funcion(params)
    }

    @Transactional
    def save(Funcion funcion) {
        if (funcion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (funcion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond funcion.errors, view:'create'
            return
        }

        funcion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'funcion.label', default: 'Funcion'), funcion.id])
                redirect funcion
            }
            '*' { respond funcion, [status: CREATED] }
        }
    }

    def edit(Funcion funcion) {
        respond funcion
    }

    @Transactional
    def update(Funcion funcion) {
        if (funcion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (funcion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond funcion.errors, view:'edit'
            return
        }

        funcion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'funcion.label', default: 'Funcion'), funcion.id])
                redirect funcion
            }
            '*'{ respond funcion, [status: OK] }
        }
    }

    @Transactional
    def delete(Funcion funcion) {

        if (funcion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        funcion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'funcion.label', default: 'Funcion'), funcion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'funcion.label', default: 'Funcion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
