package gestem


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UsuarioInterceptor)
class UsuarioInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test usuario interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"usuario")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
