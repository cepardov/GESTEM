package gestem

import static org.springframework.http.HttpStatus.*
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_LEVEL0','ROLE_LEVELX'])
@Transactional(readOnly = true)
class CursoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "POST"]

    def index(Integer max,Curso curso) {
        def cursoList = Curso.list(params)
        def educacionList = Educacion.findAllWhere(enable: true)
        params.max = Math.min(max ?: 10, 100)
        if(params.id!=null){
            respond curso, model:[cursoCount: Curso.count(), cursoList:cursoList, educacionList:educacionList]
        }else{
            respond new Curso(params), model:[cursoCount: Curso.count(), cursoList:cursoList, educacionList:educacionList]
        }
    }

    def show(Curso curso) {
        respond(curso)
    }

    def create() {
        respond new Curso(params)
    }

    @Transactional
    def save(Curso curso) {
        if (curso == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (curso.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond curso.errors, view:'index'
            return
        }

        curso.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'curso.label', default: 'Curso'), curso.id, curso.code, curso.name, ''])
                redirect(controller:"curso", action: "index")
            }
            '*' { respond curso, [status: CREATED] }
        }
    }

    def edit(Curso curso) {
        respond curso
    }

    def eliminar(Curso curso){
        curso.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'curso.label', default: 'Curso'), curso.id, curso.code, curso.name, ''])
        redirect (controller: "curso", action: "index")
    }

    @Transactional
    def update(Curso curso) {
        if (curso == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (curso.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond curso.errors, view:'index'
            return
        }

        curso.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'curso.label', default: 'Curso'), curso.id, curso.code, curso.name, ''])
                redirect(controller:"curso", action: "index")
            }
            '*'{ respond curso, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'curso.label', default: 'Curso'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
