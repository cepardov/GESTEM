package gestem

class LoginController {

    def redirection

    def index() {
        if (session.usuarioLogueado){
            //en caso de llegar al login a pesar de haber iniciado sesion, redirige en base a su tipo
            flash.message = "Bienvenido"
            redirect(uri:'/')
            //switch (session.usuarioLogueado.tipo) {
            //    case 1 : redirect controller: "administrador", action: "index"; break
            //    case 2 : redirect controller: "ingeniero", action: "index"; break
            //}
        } else {
            redirect(uri: 'http://gestem.liceopac.cl:8080')
        }
    }
    def login(){
        redirection = params.r
        printf("\n")
    }

    def signin = {
        if (request.method == 'POST') {
            def u = Usuario.findByUsuarioAndClave(params.username, params.password)
            if (u) {
                //si datos son correctos
                flash.message = "Bienvenido $u.nombre $u.paterno"
                session.usuarioLogueado = u
                //switch (u.tipo) {
                //    case 1 : redirect controller: "administrador", action: "index"; break
                //    case 2 : redirect controller: "ingeniero", action: "index"; break
                //}
                if (redirection != null){
                    redirect(uri: redirection)
                } else {
                    redirect controller: "dashboard", action: "index"
                }
            } else {
                flash.message = "Usuario o clave incorrecta"
                redirect controller: "login", action: "login"
            }
        }
    }

    def logout = {
        session.invalidate()
        flash.message = "Sesión cerrada correctamente"
        redirect(uri:'/')
    }

}