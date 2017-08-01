package gestem


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TelefonoInterceptor)
class TelefonoInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test telefono interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"telefono")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
