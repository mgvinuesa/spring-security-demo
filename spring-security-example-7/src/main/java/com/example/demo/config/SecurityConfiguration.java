package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

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
                                .antMatchers("/h2*", "/h2/*", "/login")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                ).csrf(c -> c.disable())
                .formLogin(withDefaults())
                .logout(withDefaults()).headers(headers -> headers.frameOptions().disable());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

		var user1 = User.withUsername("Andres").password("12345").authorities("ROLE_STAFF").build();
		/**
		 * acl_sid -> (2, 1, 'Andres'),
		 * 
		 * 
		 * acl_entry
		 * 
		 * Public Product
		 *      |
		 *      |    SID (Pepe y ROLE_ADMIN)
		 *      |     |
		 *      |     |  org.springframework.security.acls.domain.BasePermision (1 lectura y 2 escritura)
		 *      |     |  |
		 * 	(1, 1, 1, 1, 1, 1, 1, 1),
		   	(2, 1, 2, 1, 2, 1, 1, 1),
		   	(3, 1, 3, 3, 1, 1, 1, 1),
		   	
		  Private Product 	
		        |
		 *      |     SID (Andrés y ROLE_ADMIN)
		 *      |      |		        
		   	(4, 2, 1, 2, 1, 1, 1, 1),
			(5, 2, 2, 3, 1, 1, 1, 1),
			
		  Confidential Product
		        |
		 *      |    SID (ROLE_ADMIN)
		 *      |     |		        
			(6, 3, 1, 3, 1, 1, 1, 1),
			(7, 3, 2, 3, 2, 1, 1, 1);
		 */
		userDetailsService.createUser(user1);

		var user2 = User.withUsername("Pepe").password("12345").authorities("ROLE_STAFF").build();

		userDetailsService.createUser(user2);
		
		var user3 = User.withUsername("Manuel").password("12345").authorities("ROLE_ADMIN").build();
		
		userDetailsService.createUser(user3);

		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());

	}

}
