package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_SUPERADMIN')
@Transactional(readOnly = true)
class DireccionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def idUser

    def index(Integer max,Direccion direccion) {
        def direccionByUser = Direccion.findAllByUser(User.findById(params.idUser))
        def direccions = Direccion.list(params)

        idUser = params.idUser

        params.max = Math.min(max ?: 10, 100)

        if(params.id!=null){
            if(params.idUser!=null){
                respond direccion, model:[direccionCount: Direccion.countByUser(User.findById(params.idUser)), direccionList: direccionByUser]
            } else {
                respond direccion, model:[direccionCount: Direccion.count(), direccionList:direccions]
            }
        } else if(params.idUser!=null){
            respond new Direccion(params), model:[direccionCount: Direccion.countByUser(User.findById(params.idUser)), direccionList: direccionByUser]
        } else {
            respond new Direccion(params), model:[direccionCount: Direccion.count(), direccionList:direccions]
        }

    }

    def show(Direccion direccion) {
        respond direccion
    }

    def create() {
        respond new Direccion(params)
    }

    @Transactional
    def save() {

        if(params.comuna.empty){
            flash.message = "Debe completar el campo comuna correctamente."
            redirect(controller: "direccion", action: "index")

        } else if (params.user.empty){
            flash.message = "Debe completar el campo user correctamente."
            redirect(controller: "direccion", action: "index")
        } else {
            String[] comunaSplit = ((String) params.comuna).split('|')
            String[] userSplit = ((String) params.user).split('|')

            if(!comunaSplit.grep("|")){
                flash.message = "Debe seleccionar una comuna de la lista, en el campo comuna correctamente."
                redirect(controller: "direccion", action: "index")
            } else if(!userSplit.grep("|")){
                flash.message = "Debe seleccionar un user de la lista, en el campo user correctamente."
                redirect(controller: "direccion", action: "index")
            } else {
                try {
                    printf('\nUser:'+userSplit[0])
                    params.comuna = Comuna.findById(comunaSplit[0]).id
                    params.user = User.findById(userSplit[0]).id
                } catch (Exception e){
                    println('\nError Save try:'+e.getMessage())
                    flash.message = "Error al guardar dirección En el campo comuna y/o usaurio debe seleccionar una opción válida"
                    redirect(controller: "direccion", action: "index")
                }

                def direccion = new Direccion(params)

                if (direccion == null) {
                    transactionStatus.setRollbackOnly()
                    notFound()
                    return
                }

                if (direccion.hasErrors()) {
                    transactionStatus.setRollbackOnly()
                    respond direccion.errors, view:'create'
                    return
                }

                direccion.save flush:true

                printf('\nr:'+params.r)

                request.withFormat {
                    form multipartForm {
                        flash.message = message(code: 'default.created.message', args: [message(code: 'direccion.label', default: 'Direccion'), direccion.id])

                        if(params.r != "showUser"){
                            redirect (controller: "direccion", action: "index")
                        } else {
                            redirect(controller: "user", action: "show", id: params.idUser, params: [name : params.name, lastName : params.lastName])
                        }
                    }
                    '*' { respond direccion, [status: CREATED] }
                }
            }
        }
    }

    def edit(Direccion direccion) {
        respond direccion
    }

    def eliminar(){
        def direccion = Direccion.get(params.id)
        direccion.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'direccion.label', default: 'Direccion'), direccion.id])
        if(params.r != "showUser"){
            redirect (controller: "direccion", action: "index")
        } else {
            redirect(controller: "user", action: "show", id: params.idUser, params: [name : params.name, lastName : params.lastName])
        }
    }

    @Transactional
    def update(Direccion direccion) {
        if (direccion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (direccion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond direccion.errors, view:'edit'
            return
        }

        direccion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'direccion.label', default: 'Direccion'), direccion.id])
                if(params.r != "showUser"){
                    redirect (controller: "direccion", action: "index")
                } else {
                    redirect(controller: "user", action: "show", id: params.idUser, params: [name : params.name, lastName : params.lastName])
                }
            }
            '*'{ respond direccion, [status: OK] }
        }
    }

    def delete(){
        eliminar()
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'direccion.label', default: 'Direccion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
