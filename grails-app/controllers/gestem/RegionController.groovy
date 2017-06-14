package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RegionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Region.list(params), model:[regionCount: Region.count()]
    }

    def show(Region region) {
        respond region
    }

    def create() {
        respond new Region(params)
    }

    @Transactional
    def save(Region region) {
        if (region == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (region.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond region.errors, view:'create'
            return
        }

        region.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'region.label', default: 'Region'), region.id])
                redirect region
            }
            '*' { respond region, [status: CREATED] }
        }
    }

    def edit(Region region) {
        respond region
    }

    @Transactional
    def update(Region region) {
        if (region == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (region.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond region.errors, view:'edit'
            return
        }

        region.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'region.label', default: 'Region'), region.id])
                redirect region
            }
            '*'{ respond region, [status: OK] }
        }
    }

    @Transactional
    def delete(Region region) {

        if (region == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        region.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'region.label', default: 'Region'), region.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
