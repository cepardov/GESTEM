import gestem.UserPasswordEncoderListener
import gestem.mUserDetailsService
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))
    userDetailsService(mUserDetailsService)
}
