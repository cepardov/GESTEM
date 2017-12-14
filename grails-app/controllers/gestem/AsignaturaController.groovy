package gestem

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_LEVEL0','ROLE_LEVELX'])
class AsignaturaController {

    AsignaturaService asignaturaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond asignaturaService.list(params), model:[asignaturaCount: asignaturaService.count()]
    }

    def show(Long id) {
        respond asignaturaService.get(id)
    }

    def create() {
        respond new Asignatura(params)
    }

    def save(Asignatura asignatura) {
        if (asignatura == null) {
            notFound()
            return
        }

        try {
            asignaturaService.save(asignatura)
        } catch (ValidationException e) {
            respond asignatura.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'asignatura.label', default: 'Asignatura'), asignatura.id])
                redirect asignatura
            }
            '*' { respond asignatura, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond asignaturaService.get(id)
    }

    def update(Asignatura asignatura) {
        if (asignatura == null) {
            notFound()
            return
        }

        try {
            asignaturaService.save(asignatura)
        } catch (ValidationException e) {
            respond asignatura.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'asignatura.label', default: 'Asignatura'), asignatura.id])
                redirect asignatura
            }
            '*'{ respond asignatura, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        asignaturaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'asignatura.label', default: 'Asignatura'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
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
