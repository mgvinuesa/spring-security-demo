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
				http
                .authorizeRequests((requests) -> requests
                                .antMatchers("/login")
                                	.permitAll()
                                .antMatchers("/secured")
                                  .hasRole("STAFF")
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
		    
		    var user3 = User.withUsername("Andres")   
	                .password("12345")      
	                .authorities("ROLE_STAFF")           
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
	
	
//	@Bean
//	public RoleHierarchy roleHierarchy() {
//	    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//	    String hierarchy = "ROLE_ADMIN > ROLE_STAFF \n > ROLE_USER";
//	    roleHierarchy.setHierarchy(hierarchy);
//	    return roleHierarchy;
//	}


}
