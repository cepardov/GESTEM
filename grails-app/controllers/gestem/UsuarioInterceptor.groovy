package gestem


class UsuarioInterceptor {

    boolean before() {
        if (session.usuarioLogueado){
            true
        } else {
            flash.message = "Para ingresar a esta pagina debe iniciar sesión"
            redirect controller: "login", action: "login", params: [r: request.getRequestURI()]
        }
    }
    boolean after() {
        true
    }

    void afterView() {
        // no-op
    }
}
