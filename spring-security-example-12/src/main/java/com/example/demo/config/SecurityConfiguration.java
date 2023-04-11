package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	// @formatter:off
	protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((requests) -> requests
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .csrf(csrf -> csrf.csrfTokenRepository(new LazyCsrfTokenRepository(new CookieCsrfTokenRepository())))
                .headers(headers -> headers.disable())
                .sessionManagement(management -> 
                	 //management.sessionFixation().none());
                	//management.sessionFixation().newSession());
        			management.sessionFixation().migrateSession());
        		
        
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//TODO: Change to UserDetailsService type and explain why createUser is not in the interface
		InMemoryUserDetailsManager userDetailsService = 
		        new InMemoryUserDetailsManager();  
		    var user = User.withUsername("Manuel")   
		                .password("12345")      
		                .authorities("read")           
		                .build();                     

		    userDetailsService.createUser(user);        

		    auth.userDetailsService(userDetailsService) 
		        .passwordEncoder(NoOpPasswordEncoder.getInstance());;
	}
	

}
