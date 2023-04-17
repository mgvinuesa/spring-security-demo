package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

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
		UserDetails user1 = User.withUsername("Manuel")
	            .password(NoOpPasswordEncoder.getInstance().encode("12345"))
	            .roles("USER")
	            .build();
	        
	        UserDetails user2 = User.withUsername("Jose")
	                .password(NoOpPasswordEncoder.getInstance().encode("12345"))
	                .roles("STAFF")
	                .build();

			InMemoryUserDetailsManager userDetailsService  = new InMemoryUserDetailsManager(user1, user2);
		    auth.userDetailsService(userDetailsService) 
		        .passwordEncoder(NoOpPasswordEncoder.getInstance());;
	}


}
