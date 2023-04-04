package com.example.demo.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.captcha.CaptchaService;
import com.example.demo.config.login.CustomAuthenticationProvider;
import com.example.demo.config.login.CustomUsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CaptchaService captchaService;

	@Override
	// @formatter:off
	protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                                .antMatchers("/login")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin(login -> login
                        .loginPage("/login"))
                .logout(logout -> logout
                        .logoutUrl("/logout"));
	}

	public Filter authenticationFilter() throws Exception {
		CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter(authenticationManagerBean());
		filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=true"));
		return filter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider(userDetailsService(), passwordEncoder()));
	}
	
	/*
	 * userDetailsService(userDetailsService()) 
		 .passwordEncoder(passwordEncoder()).and()
	 */
	
	@Override
	@Bean
	public UserDetailsService userDetailsService(){
		InMemoryUserDetailsManager userDetailsService = 
		        new InMemoryUserDetailsManager();  
		    var user = User.withUsername("Manuel")   
		                .password("12345")      
		                .authorities("read")           
		                .build();                     

		    userDetailsService.createUser(user);   
		    return userDetailsService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationProvider authProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
	    	CustomAuthenticationProvider provider  = new CustomAuthenticationProvider(userDetailsService, passwordEncoder, captchaService);
	        return provider;
	}
	


}
