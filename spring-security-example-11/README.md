In the example number eleven we are going to see how to handle the failed login attempts

* Login attempts
  * Explore the spring security class DefaultAuthenticationEventPublisher, add breakpoints in publishAuthenticationFailure
  * See the class AuthenticationFailureListener, we are saving the login attempts.
  * Run the application and try to login with bad credentials.
  * See the CustomAuthenticationFailureHandler to handle how to change the message in base on the exception
* Captcha
  * Explore the login.html and the login controller.
  * Now we are going to explore how to handle add new fields in the login authentication mechanism.
  * See the new AuthenticationProvider and its methods.
    * See the new UsernamePasswordAuthenticationToken
    * See the new CustomUsernamePasswordAuthenticationFilter
