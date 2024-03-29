package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	// @formatter:off
	protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement(management -> 
                	management
                		
                		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                		.maximumSessions(2))
                 	//.maxSessionsPreventsLogin(true)
                	// SessionCreationPolicy.STATELESS
                	// This stateless architecture plays well with REST APIs and their Statelessness constraint. They also work well with authentication mechanisms such as Basic and Digest Authentication.
                .authorizeRequests((requests) -> requests
                                .antMatchers("/login")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(withDefaults())
                .logout(withDefaults());    
	}
	  
//	@Bean
//	public HttpSessionEventPublisher httpSessionEventPublisher() {
//	    return new HttpSessionEventPublisher();
//	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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
