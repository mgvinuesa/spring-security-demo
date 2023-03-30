package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	// @formatter:off
	protected void configure(HttpSecurity http) throws Exception {
        // org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
		// 1. Add custom entry point and access without be logged, to avoid login redirection, the default one is org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
//            http.exceptionHandling(handling -> handling.authenticationEntryPoint((request, response, authException) -> {
//            	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "You can't be here!!!!");
//            }))
				http
                .authorizeRequests((requests) -> requests
                                .antMatchers("/login")
                                	.permitAll()
//                                .antMatchers("/secured")
//                                	.hasAuthority("WRITE")
//                                .antMatchers("/secured")
//                                  .access("hasAuthority('WRITE') and !hasAuthority('DELETE')")
//                                .antMatchers("/secured")
//                                  .hasRole("ADMIN")
//                                .antMatchers("/secured")
//                                  .access("T(java.time.LocalTime).now().isAfter(T(java.time.LocalTime).of(12, 0))")
//                                .mvcMatchers(HttpMethod.GET, "/secured")
//                                    .hasRole("ADMIN")
// There are only examples
//                                .mvcMatchers(HttpMethod.GET, "products/{code:^[0-9]*$}")
//                                  .hasRole("ADMIN")
//                                .mvcMatchers("/email/{email:.*(.+@.+\\.com)}")
//                                   .permitAll()
                                .anyRequest()
                                	.authenticated()
                                

                ).formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/perform_login"));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManager userDetailsService = 
		        new InMemoryUserDetailsManager();  
		    var user = User.withUsername("Manuel")   
		                .password("12345")      
		                .authorities("READ")           
		                .build();                     

		    userDetailsService.createUser(user);        
		    
		    var user2 = User.withUsername("Jose")   
	                .password("67890")      
	                .authorities("WRITE")           
	                .build();        
		    
		    userDetailsService.createUser(user2);
		    
		    var user3 = User.withUsername("Andres")   
	                .password("12345")      
	                .authorities("WRITE", "DELETE")           
	                .build();  

		    userDetailsService.createUser(user3);
		    
		    var user4 = User.withUsername("Pepe")   
	                .password("12345")      
	                .authorities("ROLE_ADMIN")           
	                .build();  
		    
		    userDetailsService.createUser(user4);

		    auth.userDetailsService(userDetailsService) 
		        .passwordEncoder(NoOpPasswordEncoder.getInstance());;
	}
	


}
