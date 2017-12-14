package gestem

import static org.springframework.http.HttpStatus.*
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_LEVEL0','ROLE_LEVELX'])
@Transactional(readOnly = true)
class InstitucionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max,Institucion institucion) {
        def institucionsBySostenedor = Institucion.findAllBySostenedor(Sostenedor.findById(params.sostenedorId))
        def institucions = Institucion.list(params)
        def sostenedor = Sostenedor.findAll()

        params.max = Math.min(max ?: 20, 100)
        if(params.id!=null&&params.sostenedorId!=null){
            respond institucion, model:[institucionCount: Institucion.countBySostenedor(Sostenedor.findById(params.sostenedorId)), institucionList:institucionsBySostenedor]
        }else if(params.id!=null){
            respond institucion, model:[institucionCount: Institucion.count(), institucionList:institucions]
        }else if(params.sostenedorId!=null) {
            respond new Institucion(params), model: [institucionCount: Institucion.countBySostenedor(Sostenedor.findById(params.sostenedorId)), institucionList: institucionsBySostenedor, sostenedorList:sostenedor]
        }else {
            respond new Institucion(params), model:[institucionCount: Institucion.count(), institucionList:institucions, sostenedorList:sostenedor]
        }

    }

    def show(Institucion institucion) {
        redirect(controller:"institucion", action: "index")
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
            respond institucion.errors, view:'index'
            return
        }

        institucion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'institucion.label', default: 'Institucion'), institucion.id, institucion.code, institucion.name, ''])
                if(params.sostenedorId!=null&&params.sostenedorId!=null&&params.sostenedorId!=''){
                    redirect(controller:"institucion", action: "index", params: [sostenedorId: params.sostenedorId,sostenedorName: params.sostenedorName])
                } else {
                    redirect(controller:"institucion", action: "index")
                }
            }
            '*' { respond institucion, [status: CREATED] }
        }
    }

    def edit(Institucion institucion) {
        respond institucion
    }

    def eliminar(Institucion institucion){
        //def institucion = Institucion.get(params.id)
        institucion.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'institucion.label', default: 'Institucion'), institucion.id, institucion.code, institucion.name, ''])

        if(params.sostenedorId!=null&&params.sostenedorId!=null&&params.sostenedorId!=''){
            redirect(controller:"institucion", action: "index", params: [sostenedorId: params.sostenedorId,sostenedorName: params.sostenedorName])
        } else {
            redirect(controller:"institucion", action: "index")
        }
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'institucion.label', default: 'Institucion'), institucion.id, institucion.code, institucion.name, ''])
                if(params.sostenedorId!=null&&params.sostenedorId!=null&&params.sostenedorId!=''){
                    redirect(controller:"institucion", action: "index", params: [sostenedorId: params.sostenedorId,sostenedorName: params.sostenedorName])
                } else {
                    redirect(controller:"institucion", action: "index")
                }
            }
            '*'{ respond institucion, [status: OK] }
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
