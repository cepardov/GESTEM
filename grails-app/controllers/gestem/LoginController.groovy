package gestem

class LoginController {

    def redirection

    def index() {
        if (session.usuarioLogueado){
            redirect(uri:'/')
            //switch (session.usuarioLogueado.tipo) {
            //    case 1 : redirect controller: "administrador", action: "index"; break
            //    case 2 : redirect controller: "ingeniero", action: "index"; break
            //}
        } else {
            redirect controller: "login", action: "login"
        }
    }
    def login(){
        redirection = params.r
        printf("\n")
    }

    def signin = {
        if (request.method == 'POST') {
            def user = Usuario.findByUsuario(params.username)
            def password = Usuario.findByUsuarioAndClave(params.username, params.password)
            printf("singin() user:"+user+"\n")
            printf("singin() password:"+password+"\n")
            if(user&&password){
                flash.message = "Bienvenido $user.nombre $user.paterno"
                session.usuarioLogueado = user
                if (redirection != null){
                    redirect(uri: redirection)
                } else {
                    redirect controller: "dashboard", action: "index"
                }
                printf("Login true\n")
            }else{
                if(user){
                    printf("Login password error\n")
                    flash.message = "Error de inicio sesión clave incorrecta"
                    redirect controller: "login", action: "login"
                } else {
                    printf("Login password and user error\n")
                    flash.message = "Error de inicio sesión usuario y contraseña incorrecta"
                    redirect controller: "login", action: "login"
                }
            }
        }
    }

    def logout = {
        session.invalidate()
        flash.message = "Sesión cerrada correctamente"
        redirect(uri:'/')
    }

}