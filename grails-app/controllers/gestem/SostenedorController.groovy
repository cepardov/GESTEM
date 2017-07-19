package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SostenedorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def comunaId
    def comunaName

    def index(Integer max,Sostenedor sostenedor) {
        def sostenedorsByComuna = Sostenedor.findAllByComuna(Comuna.findById(params.comunaId))
        def sostenedors = Sostenedor.list(params)
        def comuna = Comuna.findAll()

        comunaId = params.comunaId
        comunaName = params.comunaName

        params.max = Math.min(max ?: 20, 100)

        if(params.id!=null){
            respond sostenedor, model:[sostenedorCount: Sostenedor.count(), sostenedorList:sostenedors]
        }else if(params.comunaId!=null) {
            respond new Sostenedor(params), model: [sostenedorCount: Sostenedor.countByComuna(Comuna.findById(params.comunaId)), sostenedorList: sostenedorsByComuna, comunaList:comuna]
        }else {
            respond new Sostenedor(params), model:[sostenedorCount: Sostenedor.count(), sostenedorList:sostenedors, comunaList:comuna]
        }

    }

    def show(Sostenedor sostenedor) {
        redirect(controller:"sostenedor", action: "index")
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
            respond sostenedor.errors, view:'index'
            return
        }

        sostenedor.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sostenedor.label', default: 'Sostenedor'), sostenedor.id, sostenedor.code, sostenedor.name, ''])
                redirect(controller:"sostenedor", action: "index", params: [comunaId: comunaId,comunaName: comunaName])
            }
            '*' { respond sostenedor, [status: CREATED] }
        }
    }

    def edit(Sostenedor sostenedor) {
        respond sostenedor
    }

    def eliminar(Sostenedor sostenedor){
        //def sostenedor = Sostenedor.get(params.id)
        sostenedor.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'sostenedor.label', default: 'Sostenedor'), sostenedor.id, sostenedor.code, sostenedor.name, ''])
        redirect (controller: "sostenedor", action: "index", params: [comunaId: comunaId,comunaName: comunaName])
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sostenedor.label', default: 'Sostenedor'), sostenedor.id, sostenedor.code, sostenedor.name, ''])
                redirect(controller:"sostenedor", action: "index",  params: [comunaId: comunaId,comunaName: comunaName])
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
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sostenedor.label', default: 'Sostenedor'), sostenedor.id, sostenedor.code, sostenedor.name, ''])
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
