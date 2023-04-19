In the example number one we are going to explore the default configurations provided by Spring Security.

* Check the code. Is there something related with spring-security?
* Run the spring boot application using de property debug=true.
* Explore the logs.

* (Optional) Add breakpoints in the following classes:
  * org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration -> Register the @Bean springSecurityFilterChain.
  * org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration -> The default configuration for web security.
  * org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration  -> Adds an InMemoryUserDetailsManager with a default user and generated password.


* Review the @Conditionals in each class, see in which JAR are located some of them. spring-boot-autoconfigure

