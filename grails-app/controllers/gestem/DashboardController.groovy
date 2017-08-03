package gestem

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_SUPERADMIN')
class DashboardController {

    def index() { }
}
