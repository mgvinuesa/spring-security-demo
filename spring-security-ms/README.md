# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.3/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.3/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.0.3/reference/htmlsingle/#web.security)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.3/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Spring security in depth

* [Spring Security Architecture](https://spring.io/guides/topicals/spring-security-architecture/)


#1. Creating new project
1. Starter Request -> [Link](https://start.spring.io/starter.zip?name=spring-security-ms&groupId=com.example.security&artifactId=spring-security-ms&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.example.demo&type=maven-project&packaging=jar&javaVersion=17&language=java&bootVersion=3.0.3&dependencies=lombok&dependencies=data-jpa&dependencies=h2&dependencies=security&dependencies=web)

2. Create the @RestController with default configuration

3. Run

```
Using generated security password: bd0b0134-35a3-4b72-940c-132c2469c246

This generated password is for development use only. Your security configuration must be updated before running your application in production.
```

```
curl --location --request GET 'http://localhost:8080/hello' \
--header 'Authorization: Basic dXNlcjpiZDBiMDEzNC0zNWEzLTRiNzItOTQwYy0xMzJjMjQ2OWMyNDY=' \
--header 'Cookie: JSESSIONID=88FF74BDF8F185EF72CCB8B783F0698B'
```

#2. Override default @Beans

* [BCript Encoder](https://bcrypt-generator.com/): Strength the log rounds to use, between 4 and 31

#3.



#Bibliografía.

## Examples
* https://www.baeldung.com/spring-security-login
* https://github.com/spring-projects/spring-security-samples
* https://www.baeldung.com/spring-security-jdbc-authentication
* https://www.baeldung.com/spring-security-authentication-with-a-database
* https://github.com/Baeldung/spring-security-registration
* https://www.baeldung.com/spring-security-restrict-authentication-by-geography
* https://github.com/eugenp/tutorials/tree/master/spring-security-modules
* https://github.com/eugenp/tutorials/tree/master/spring-security-modules/spring-security-web-mvc
* https://stackoverflow.com/questions/46806761/redirect-to-change-pass-page-for-users-with-expired-password
* https://github.com/eugenp/tutorials/tree/master/spring-security-modules/spring-security-acl

## Sesion
* https://www.baeldung.com/spring-security-session
* https://github.com/eugenp/tutorials/tree/master/spring-web-modules/spring-session
* https://www.techgeeknext.com/spring-boot/spring-boot-session-management

## Buenas practicas
* https://snyk.io/blog/spring-boot-security-best-practices/

## Spring Security Appendix
* https://docs.spring.io/spring-security/reference/servlet/appendix/database-schema.html#_user_schema

## Spring Boot Production Ready
https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html
https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.security