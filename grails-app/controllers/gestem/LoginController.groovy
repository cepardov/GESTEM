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

    def logout = {
        redirect(uri:'/logoff')
    }

}