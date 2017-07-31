package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UsuarioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max,Usuario usuario) {
        def usuarios = Usuario.list(params)

        params.max = Math.min(max ?: 10, 100)
        if(params.id!=null){
            respond usuario, model:[usuarioCount: Usuario.count(), usuarioList:usuarios]
        }else{
            respond new Usuario(params), model:[usuarioCount: Usuario.count(), usuarioList:usuarios]
        }

    }

    def show(Usuario usuario) {
        def direccionList = Direccion.findAllByUsuario(Usuario.findById(params.id))
        def telefonoList = Telefono.findAllByUsuario(Usuario.findById(params.id))
        def correoList = Correo.findAllByUsuario(Usuario.findById(params.id))
        def direccion
        def telefono
        def correo
        if(params.idDireccion){
            direccion = Direccion.findById(params.idDireccion).address
        }
        if(params.idTelefono){
            telefono = Telefono.findById(params.idTelefono).phoneNumber
        }
        if(params.idCorreo){
            correo = Correo.findById(params.idCorreo).email
        }
        respond usuario, model:[direccionList:direccionList, direccion:direccion, telefonoList:telefonoList, telefono:telefono, correoList:correoList, correo:correo]
    }

    def create() {
        respond new Usuario(params)
    }

    @Transactional
    def save(Usuario usuario) {
        if (usuario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuario.errors, view:'create'
            return
        }

        usuario.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuario.id, usuario.nombre, usuario.paterno, usuario.materno])
                redirect(controller:"usuario", action: "index")
            }
            '*' { respond usuario, [status: CREATED] }
        }
    }

    def edit(Usuario usuario) {
        respond usuario
    }

    def eliminar(){
        def usuario = Usuario.get(params.id)
        usuario.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuario.id, usuario.nombre, usuario.paterno, usuario.materno])
        redirect (controller: "usuario", action: "index")
    }

    @Transactional
    def update(Usuario usuario) {
        if (usuario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuario.errors, view:'edit'
            return
        }

        usuario.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuario.id, usuario.nombre, usuario.paterno, usuario.materno])
                redirect(controller:"usuario", action: "index")
            }
            '*'{ respond usuario, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
