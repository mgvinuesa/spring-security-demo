# Obtención del usuario

En la clase SecurityConfiguration se ha definido los sistemas de autenticación siguientes

* Form
* Basic

```
protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests((requests) -> requests
				.anyRequest().authenticated()
			)
			.httpBasic(withDefaults())
			.formLogin(withDefaults());
	}
```


Quitar una de las opciones y volver a probar. Ejemplo de Auth Basic

```
curl --location 'http://localhost:8080/' \
--header 'Authorization: Basic dXNlcjo5ZDhhODQxYy01ZGJmLTQ0MTktYTM3OC1mZTc2MGRmMTk1Yzk=' \
--header 'Cookie: JSESSIONID=2F9A715926D2C8C051EBA43B36F19C9D'
```

# Revisar las dos maneras de añadir customizaciones

1. Añadir @Beans al contexto.
2. Modificar a través del @Override de WebSecurityConfigurerAdapter

# Customización de la gestión de usuarios.

https://docs.spring.io/spring-security/reference/5.7/servlet/authentication/passwords/storage.html

Soporta varios tipos de almacenamiento:

* Memoria

* JDBC: -> org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl

Uso de una BD externa para la gestión de usuarios.

# Customización de métodos de autenticación, CustomAuthenticationProvider
