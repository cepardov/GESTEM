package gestem

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_SUPERADMIN','ROLE_ADMIN'])
class DashboardController {

    def index() { }
}
