package com.example.demo.config;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.example.demo.config.handlers.CustomLogoutSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	// @formatter:off
	protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((requests) -> requests
                		 .antMatchers("/css/**", "/login")
                         .permitAll()
                         .anyRequest()
                         .authenticated()
                )
                .formLogin(login -> login
                		.loginPage("/login") // Marcar la misma URL que por defecto no hace que se comporte igual
                        .loginProcessingUrl("/perform_login")
                        .failureHandler(failureHandler())
                        .defaultSuccessUrl("/secured", true)) // El true es "alwaysUse"
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logoutOk") //Por defecto va a login?logout
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(logoutSuccessHandler())
                        )
                ;
	}

	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}

	@Bean
	public SimpleUrlAuthenticationFailureHandler failureHandler() {
		//Está devolviendo un 200
	    return new SimpleUrlAuthenticationFailureHandler("/login?error=true");
	}
	
	@Bean
	public AuthenticationFailureHandler customFailureHandler() {
		return new AuthenticationFailureHandler() {
			
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
		        Map<String, Object> data = new HashMap<>();
		        data.put(
		          "timestamp", 
		          Calendar.getInstance().getTime());
		        data.put(
		          "exception", 
		          exception.getMessage());

		        response.getOutputStream()
		          .println(new ObjectMapper().writeValueAsString(data));
			}
		};
	}

	  

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
