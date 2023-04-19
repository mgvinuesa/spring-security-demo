package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	// @formatter:off
	protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((requests) -> requests
                		 .antMatchers("/private/**")
                         .hasRole("USER")
                         .antMatchers("/public/**")
                         .permitAll()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults()); 		
        
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		    auth.userDetailsService(userDetailsService) 
		        .passwordEncoder(NoOpPasswordEncoder.getInstance());;
	}


}
