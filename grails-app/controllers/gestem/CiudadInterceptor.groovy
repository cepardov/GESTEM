package gestem

/**
 * Created by cepardov on 19-07-17.
 */
class CiudadInterceptor {
    boolean before() {
        if (session.usuarioLogueado){
            true
        } else {
            flash.message = "Para ingresar a esta pagina debe iniciar sesión"
            printf("PaisInterceptor getRequestURI:"+request.getRequestURI()+"\n")
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
