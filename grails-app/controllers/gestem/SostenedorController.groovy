package gestem

import static org.springframework.http.HttpStatus.*
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_LEVEL0','ROLE_LEVELX'])
@Transactional(readOnly = true)
class SostenedorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def loggedUserInfo
    def comunaId
    def comunaName

    def index(Integer max,Sostenedor sostenedor) {
        //Debe mostrar solo el sostenedor adjunto a la institucion
        loggedUserInfo = User.findByUsername(sec.username())

        if (loggedUserInfo.institucion){
            def sostenedorList = Sostenedor.findAllWhere(id: loggedUserInfo.institucion.id)
            def comunaList = Comuna.findAll()
            comunaId = params.comunaId
            comunaName = params.comunaName

            params.max = Math.min(max ?: 20, 100)

            //Bug cuando el usuario de institucion puede ver
            //datos de otros sostenedores cambiando en la url el id
            println "params.id="+params.id
            println "user sosid="+loggedUserInfo.institucion.sostenedor.id+"\n"

            if (params.id!=null){
                println "Tiene ID="+params.id+"\n"
                if (loggedUserInfo.institucion.sostenedor.id == params.id){
                    println "son iguales"
                } else {
                    println "son diferentes"
                    //redirect(controller:"sostenedor", action: "index")
                }
            }else{
                println "No tiene id"
            }

            if(params.id!=null){
                respond sostenedor, model:[sostenedorCount: Sostenedor.count(), sostenedorList:sostenedorList]
            }else {
                respond new Sostenedor(params), model:[sostenedorCount: Sostenedor.count(), sostenedorList:sostenedorList, comunaList:comunaList]
            }
        } else {
            def sostenedorList = Sostenedor.list()
            def comunaList = Comuna.findAll()
            comunaId = params.comunaId
            comunaName = params.comunaName

            params.max = Math.min(max ?: 20, 100)

            if(params.id!=null){
                respond sostenedor, model:[sostenedorCount: Sostenedor.count(), sostenedorList:sostenedorList]
            }else {
                respond new Sostenedor(params), model:[sostenedorCount: Sostenedor.count(), sostenedorList:sostenedorList, comunaList:comunaList]
            }
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
        if (loggedUserInfo.institucion){
            flash.message = "No se puede realiazr esta accion con una cuenta institucional."
            redirect(controller:"sostenedor", action: "index")
            return
        }

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
                redirect(controller:"sostenedor", action: "index")
            }
            '*' { respond sostenedor, [status: CREATED] }
        }
    }

    def edit(Sostenedor sostenedor) {
        respond sostenedor
    }

    def eliminar(Sostenedor sostenedor){
        if (loggedUserInfo.institucion){
            flash.message = "No se puede realiazr esta accion con una cuenta institucional."
            redirect(controller:"sostenedor", action: "index")
            return
        }

        sostenedor.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'sostenedor.label', default: 'Sostenedor'), sostenedor.id, sostenedor.code, sostenedor.name, ''])
        redirect (controller: "sostenedor", action: "index")
    }

    @Transactional
    def update(Sostenedor sostenedor) {
        if (loggedUserInfo.institucion){
            flash.message = "No se puede realiazr esta accion con una cuenta institucional."
            redirect(controller:"sostenedor", action: "index")
            return
        }

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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sostenedor.label', default: 'Sostenedor'), sostenedor.id, sostenedor.code, sostenedor.name, ''])
                redirect(controller:"sostenedor", action: "index")
            }
            '*'{ respond sostenedor, [status: OK] }
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
