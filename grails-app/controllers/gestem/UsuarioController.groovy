package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class UsuarioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured('ROLE_SUPERADMIN')
    def index(Integer max,Usuario usuario) {
        def usuarios = Usuario.list(params)
        params.max = Math.min(max ?: 10, 100)
        if(params.id!=null){
            respond usuario, model:[usuarioCount: Usuario.count(), usuarioList:usuarios]
        }else{
            respond new Usuario(params), model:[usuarioCount: Usuario.count(), usuarioList:usuarios]
        }

    }

    def getFechaNacimiento(Usuario usuario){
        def fechaNacimiento
        printf('\ngetFechaNacimiento in:'+usuario.fechaNacimiento)
        printf('\nid usuario:'+usuario.id)

        if(usuario.fechaNacimiento){
            def fechaNacimientoIn = usuario.fechaNacimiento.toString().replace(' ',' | ')
            printf('\nFecha remplace:'+fechaNacimientoIn)
            String[] fechaNacimientoSplit0 = fechaNacimientoIn.split(' | ')
            String[] fechaNacimientoSplit1 = fechaNacimientoSplit0[0].split('-')

            def dd = fechaNacimientoSplit1[2]
            def mm = fechaNacimientoSplit1[1]
            def yyyy = fechaNacimientoSplit1[0]

            fechaNacimiento = dd+'/'+mm+'/'+yyyy
            printf('\nFinal Fecha:'+ fechaNacimiento)

        }

        return fechaNacimiento
    }

    @Secured('ROLE_SUPERADMIN')
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
        respond usuario, model:[direccionList:direccionList, direccion:direccion, telefonoList:telefonoList, telefono:telefono, correoList:correoList, correo:correo, fechaNacimientoOut: getFechaNacimiento()]
    }

    @Secured('ROLE_SUPERADMIN')
    def create() {
        respond new Usuario(params)
    }

    @Secured('ROLE_SUPERADMIN')
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

    //def edit(Usuario usuario) {
    //    respond usuario
    //}

    @Secured('ROLE_SUPERADMIN')
    def eliminar(){
        def usuario = Usuario.get(params.id)
        usuario.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuario.id, usuario.nombre, usuario.paterno, usuario.materno])
        redirect (controller: "usuario", action: "index")
    }

    @Secured('ROLE_SUPERADMIN')
    @Transactional
    def update(Usuario usuario) {
        if(params.fechaNacimientoDat){
            printf('\nFecha Inicial:'+params.fechaNacimientoDat+'\n')
            String[] fechaNacimientoSplit = ((String) params.fechaNacimientoDat).split('/')
            def dd = fechaNacimientoSplit[0]
            def mm = fechaNacimientoSplit[1]
            def yyyy = fechaNacimientoSplit[2]
            Date date = new Date().parse("d/M/yyyy H:m:s", dd+'/'+mm+'/'+yyyy+' 00:00:00.0')
            usuario.setFechaNacimiento(date)
        } else {

        }

        if (usuario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuario.errors, view:'index'
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

    @Secured('ROLE_SUPERADMIN')
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
