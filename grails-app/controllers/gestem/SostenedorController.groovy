package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SostenedorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sostenedor.list(params), model:[sostenedorCount: Sostenedor.count()]
    }

    def show(Sostenedor sostenedor) {
        respond sostenedor
    }

    def create() {
        respond new Sostenedor(params)
    }

    @Transactional
    def save(Sostenedor sostenedor) {
        if (sostenedor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sostenedor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sostenedor.errors, view:'create'
            return
        }

        sostenedor.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sostenedor.label', default: 'Sostenedor'), sostenedor.id])
                redirect sostenedor
            }
            '*' { respond sostenedor, [status: CREATED] }
        }
    }

    def edit(Sostenedor sostenedor) {
        respond sostenedor
    }

    @Transactional
    def update(Sostenedor sostenedor) {
        if (sostenedor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sostenedor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sostenedor.errors, view:'edit'
            return
        }

        sostenedor.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sostenedor.label', default: 'Sostenedor'), sostenedor.id])
                redirect sostenedor
            }
            '*'{ respond sostenedor, [status: OK] }
        }
    }

    @Transactional
    def delete(Sostenedor sostenedor) {

        if (sostenedor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        sostenedor.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sostenedor.label', default: 'Sostenedor'), sostenedor.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sostenedor.label', default: 'Sostenedor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
