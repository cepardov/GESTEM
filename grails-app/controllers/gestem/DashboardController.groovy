package gestem

import grails.plugin.springsecurity.annotation.Secured

class DashboardController {

    @Secured('ROLE_SUPERADMIN')
    def index() { }
}
