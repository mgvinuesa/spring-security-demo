package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.example.demo.user.CustomUserService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserService userService;

	@Override
	// @formatter:off
	protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((requests) -> requests
                		 .antMatchers("/h2", "/login", "/registration*","/user/registration", "/successRegister*", "/regitrationConfirm", "/resources/**")
                         .permitAll()
                         .anyRequest()
                         .authenticated()
                ).csrf(c -> c.disable())
                .formLogin(login -> login
                		.loginPage("/login") // Marcar la misma URL que por defecto no hace que se comporte igual
                        .loginProcessingUrl("/perform_login")) 
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        )
                ;
	}

	  

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  		    auth.userDetailsService(userService) 
		        .passwordEncoder(NoOpPasswordEncoder.getInstance());;
	}
	



}
