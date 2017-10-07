package gestem

import grails.plugin.springsecurity.annotation.Secured

class ApoderadoController {

    @Secured(['ROLE_LEVEL0','ROLE_LEVEL1'])
    def index() {

    }

    @Secured(['ROLE_LEVEL0','ROLE_LEVEL1'])
    def show() {

    }
}
