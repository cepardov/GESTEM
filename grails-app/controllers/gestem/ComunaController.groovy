package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ComunaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Comuna.list(params), model:[comunaCount: Comuna.count()]
    }

    def show(Comuna comuna) {
        respond comuna
    }

    def create() {
        respond new Comuna(params)
    }

    @Transactional
    def save(Comuna comuna) {
        if (comuna == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (comuna.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond comuna.errors, view:'create'
            return
        }

        comuna.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'comuna.label', default: 'Comuna'), comuna.id])
                redirect comuna
            }
            '*' { respond comuna, [status: CREATED] }
        }
    }

    def edit(Comuna comuna) {
        respond comuna
    }

    @Transactional
    def update(Comuna comuna) {
        if (comuna == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (comuna.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond comuna.errors, view:'edit'
            return
        }

        comuna.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'comuna.label', default: 'Comuna'), comuna.id])
                redirect comuna
            }
            '*'{ respond comuna, [status: OK] }
        }
    }

    @Transactional
    def delete(Comuna comuna) {

        if (comuna == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        comuna.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'comuna.label', default: 'Comuna'), comuna.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'comuna.label', default: 'Comuna'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
