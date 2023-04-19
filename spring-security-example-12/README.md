In the example twelve we are going to see the main attack prevention mechanisms that spring security provides:

* Session fixation:
  * Run the application and login, see the sessionId and the stored value with the different options.
* CSRF
  * Explore the cookies when use CookieCsrfTokenRepository
* XSS
  * Enable and disable the headers, see the HTTP responses check the class org.springframework.security.config.annotation.web.configurers.HeadersConfigurer

