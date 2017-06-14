package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CiudadController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Ciudad.list(params), model:[ciudadCount: Ciudad.count()]
    }

    def show(Ciudad ciudad) {
        respond ciudad
    }

    def create() {
        respond new Ciudad(params)
    }

    @Transactional
    def save(Ciudad ciudad) {
        if (ciudad == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ciudad.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ciudad.errors, view:'create'
            return
        }

        ciudad.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), ciudad.id])
                redirect ciudad
            }
            '*' { respond ciudad, [status: CREATED] }
        }
    }

    def edit(Ciudad ciudad) {
        respond ciudad
    }

    @Transactional
    def update(Ciudad ciudad) {
        if (ciudad == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ciudad.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ciudad.errors, view:'edit'
            return
        }

        ciudad.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), ciudad.id])
                redirect ciudad
            }
            '*'{ respond ciudad, [status: OK] }
        }
    }

    @Transactional
    def delete(Ciudad ciudad) {

        if (ciudad == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        ciudad.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), ciudad.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
