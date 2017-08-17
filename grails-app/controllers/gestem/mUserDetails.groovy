package gestem

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority
import grails.plugin.springsecurity.userdetails.GrailsUser

class mUserDetails extends GrailsUser {
    final String fullName
    //final String materno = "sads"
    final String institucion

    mUserDetails(String username, String password, boolean enabled,
                 boolean accountNonExpired, boolean credentialsNonExpired,
                 boolean accountNonLocked,
                 Collection<GrantedAuthority> authorities,
                 long id, String fullName, String institucion) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities, id)

        this.fullName = fullName
        this.institucion = institucion
    }
}
