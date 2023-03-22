# Conceptos Java & Spring básicos para Spring Security

* WebContext & RootContext: https://www.baeldung.com/spring-web-contexts
* Servlet & Filter
* DispatcherServlet 
* Embedded Servlet Container: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web.servlet.embedded-container
* AutoConfiguration
* Conditionals

# Spring Security Por Defecto

La versión 2.7.9 de Spring Boot contiene la versión 5.7.7 de Spring Security.

## ¿Que incluye por defecto Spring Security al añadir el starter?

[Getting Started](https://docs.spring.io/spring-security/reference/5.7/servlet/getting-started.html)

Añade, sin más, lo siguiente:

* Enables Spring Security’s default configuration, which creates a servlet Filter as a bean named springSecurityFilterChain. This bean is responsible for all the security (protecting the application URLs, validating submitted username and passwords, redirecting to the log in form, and so on) within your application.

* Creates a UserDetailsService bean with a username of user and a randomly generated password that is logged to the console.

* Require an authenticated user for any interaction with the application

* Generate a default login form for you

* Let the user with a username of user and a password that is logged to the console to authenticate with form-based authentication:

  ```
  Using generated security password: 6a253259-1e7e-48fc-9944-e5b8365780f9

  This generated password is for development use only. Your security configuration must be updated before running your application in production.
  ```

* Protects the password storage with BCrypt

* Lets the user log out

* CSRF attack prevention

* Session Fixation protection

* Security Header integration

* HTTP Strict Transport Security for secure requests

   * X-Content-Type-Options integration

   * Cache Control (can be overridden later by your application to allow caching of your static resources)

   * X-XSS-Protection integration

   * X-Frame-Options integration to help prevent Clickjacking

* Integrate with the following Servlet API methods:

  * HttpServletRequest#getRemoteUser()

  * HttpServletRequest.html#getUserPrincipal()

  * HttpServletRequest.html#isUserInRole(java.lang.String)

  * HttpServletRequest.html#login(java.lang.String, java.lang.String)

  * HttpServletRequest.html#logout()

## Revisar la clase

2. org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration -> Registra el @Bean asociado al filter en el contexto
3. org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration -> Añade la configuración básica a nivel de SecurityFilterChain (nueva impl)
4. org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration -> Crea un InMemoryUserDetailsManager para la gestión del primer usuario de prueba


* Ver los condicionales
* No está en spring-security sino spring-boot-autoconfigure

 
 ## User y UserDetails
 
  org.springframework.security.core.userdetails.UserDetails -> Es la interfaz principal para la gestión de usuarios dentro de SpringSecurity
 org.springframework.security.core.userdetails.User -> Es la implementación por defecto.

 
 ```
   private String password;

	private final String username;

	private final Set<GrantedAuthority> authorities; -> ROLES

	private final boolean accountNonExpired;

	private final boolean accountNonLocked;

	private final boolean credentialsNonExpired;

	private final boolean enabled;
 ```
