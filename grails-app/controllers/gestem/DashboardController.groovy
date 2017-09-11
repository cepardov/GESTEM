package gestem

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_LEVEL0','ROLE_LEVEL1'])
class DashboardController {

    def index() { }
}
