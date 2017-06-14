package gestem


class UsuarioInterceptor {

    //el metodo before del interceptor se ejecuta antes de procesar el controlador
    boolean before() {
        //si existe usuario logueado, entonces continua al controlador (true)
        //si no hay usuario logueado, redirige al login
        if (session.usuarioLogueado){
            true
        } else {
            flash.message = "Para ingresar a esta pagina debe iniciar sesión"
            redirect controller: "login", action: "login"
        }
    }
    //el metodo after del interceptor se ejecuta despues de procesar el controlador
    boolean after() {
        true
    }

    void afterView() {
        // no-op
    }
}
