package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ComunaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def ciudadId
    def ciudadName

    def index(Integer max,Comuna comuna) {
        def comunasByCiudad = Comuna.findAllByCiudad(Ciudad.findById(params.ciudadId))
        def comunas = Comuna.list(params)
        def ciudad = Ciudad.findAll()

        ciudadId = params.ciudadId
        ciudadName = params.ciudadName

        params.max = Math.min(max ?: 20, 100)

        if(params.id!=null){
            respond comuna, model:[comunaCount: Comuna.count(), comunaList:comunas]
        }else if(params.ciudadId!=null) {
            respond new Comuna(params), model: [comunaCount: Comuna.countByCiudad(Ciudad.findById(params.ciudadId)), comunaList: comunasByCiudad, ciudadList:ciudad]
        }else {
            respond new Comuna(params), model:[comunaCount: Comuna.count(), comunaList:comunas, ciudadList:ciudad]
        }

    }

    def show(Comuna comuna) {
        redirect(controller:"comuna", action: "index")
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
            respond comuna.errors, view:'index'
            return
        }

        comuna.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'comuna.label', default: 'Comuna'), comuna.id, comuna.code, comuna.name, ''])
                redirect(controller:"comuna", action: "index", params: [ciudadId: ciudadId,ciudadName: ciudadName])
            }
            '*' { respond comuna, [status: CREATED] }
        }
    }

    def edit(Comuna comuna) {
        respond comuna
    }

    def eliminar(Comuna comuna){
        //def comuna = Comuna.get(params.id)
        comuna.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'comuna.label', default: 'Comuna'), comuna.id, comuna.code, comuna.name, ''])
        redirect (controller: "comuna", action: "index", params: [ciudadId: ciudadId,ciudadName: ciudadName])
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'comuna.label', default: 'Comuna'), comuna.id, comuna.code, comuna.name, ''])
                redirect(controller:"comuna", action: "index",  params: [ciudadId: ciudadId,ciudadName: ciudadName])
            }
            '*'{ respond comuna, [status: OK] }
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
