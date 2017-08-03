package gestem

import grails.plugin.springsecurity.annotation.Secured

class LoginController extends grails.plugin.springsecurity.LoginController {

    //def springSecurityService
    //nuevas funciones

    def index() {
        if (springSecurityService.isLoggedIn()) {
            //printf('\nLogged ID:'+springSecurityService.currentUserId)
            //redirect uri: conf.successHandler.defaultTargetUrl
            redirect(controller: "dashboard", action: "index")
        }
        else {
            redirect action: 'auth', params: params
        }
    }

    def auth() {

        def conf = getConf()

        if (springSecurityService.isLoggedIn()) {
            redirect uri: conf.successHandler.defaultTargetUrl
            return
        }

        String postUrl = request.contextPath + conf.apf.filterProcessesUrl
        render view: 'auth', model: [postUrl: postUrl,
                                     rememberMeParameter: conf.rememberMe.parameter,
                                     usernameParameter: conf.apf.usernameParameter,
                                     passwordParameter: conf.apf.passwordParameter,
                                     gspLayout: conf.gsp.layoutAuth]
    }

    def denied() {
        if (springSecurityService.isLoggedIn() && authenticationTrustResolver.isRememberMe(authentication)) {
            // have cookie but the page is guarded with IS_AUTHENTICATED_FULLY (or the equivalent expression)
            redirect action: 'full', params: params
            return
        }
        flash.message = "No tiene los privilegios suficientes para acceder a esta página"
        //redirect controller: 'dashboard', action: 'index'
        [gspLayout: conf.gsp.layoutDenied]
    }

    def logout = {
        redirect(uri:'/logoff')
    }

}