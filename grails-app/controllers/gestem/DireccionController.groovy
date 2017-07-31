package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DireccionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def idUsuario

    def index(Integer max,Direccion direccion) {
        def direccionByUsuario = Direccion.findAllByUsuario(Usuario.findById(params.idUsuario))
        def direccions = Direccion.list(params)

        idUsuario = params.idUsuario

        params.max = Math.min(max ?: 10, 100)

        if(params.id!=null){
            if(params.idUsuario!=null){
                respond direccion, model:[direccionCount: Direccion.countByUsuario(Usuario.findById(params.idUsuario)), direccionList: direccionByUsuario]
            } else {
                respond direccion, model:[direccionCount: Direccion.count(), direccionList:direccions]
            }
        } else if(params.idUsuario!=null){
            respond new Direccion(params), model:[direccionCount: Direccion.countByUsuario(Usuario.findById(params.idUsuario)), direccionList: direccionByUsuario]
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

        } else if (params.usuario.empty){
            flash.message = "Debe completar el campo usuario correctamente."
            redirect(controller: "direccion", action: "index")
        } else {
            String[] comunaSplit = ((String) params.comuna).split('|')
            String[] usuarioSplit = ((String) params.usuario).split('|')

            if(!comunaSplit.grep("|")){
                flash.message = "Debe seleccionar una comuna de la lista, en el campo comuna correctamente."
                redirect(controller: "direccion", action: "index")
            } else if(!usuarioSplit.grep("|")){
                flash.message = "Debe seleccionar un usuario de la lista, en el campo usuario correctamente."
                redirect(controller: "direccion", action: "index")
            } else {
                try {
                    printf('\nUsuario:'+usuarioSplit[0])
                    params.comuna = Comuna.findById(comunaSplit[0]).id
                    params.usuario = Usuario.findById(usuarioSplit[0]).id
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

                        if(params.r != "showUsuario"){
                            redirect (controller: "direccion", action: "index")
                        } else {
                            redirect(controller: "usuario", action: "show", id: params.idUsuario, params: [name : params.name, lastName : params.lastName])
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
        if(params.r != "showUsuario"){
            redirect (controller: "direccion", action: "index")
        } else {
            redirect(controller: "usuario", action: "show", id: params.idUsuario, params: [name : params.name, lastName : params.lastName])
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
                if(params.r != "showUsuario"){
                    redirect (controller: "direccion", action: "index")
                } else {
                    redirect(controller: "usuario", action: "show", id: params.idUsuario, params: [name : params.name, lastName : params.lastName])
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
