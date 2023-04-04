package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.LocaleResolver;

import com.example.demo.captcha.CaptchaService;
import com.example.demo.config.login.CustomAuthenticationFailureHandler;
import com.example.demo.config.login.CustomAuthenticationProvider;
import com.example.demo.config.login.CustomUserDetailsService;
import com.example.demo.config.login.CustomUsernamePasswordAuthenticationFilter;
import com.example.demo.config.login.LoginAttemptService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private LocaleResolver locale;

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
                .logout(withDefaults());
	}

	public Filter authenticationFilter() throws Exception {
		CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter(authenticationManagerBean());
		filter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler(locale, messageSource));
		return filter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider(userDetailsService(), passwordEncoder()));
	}
	
	@Bean
	public UserDetailsService userDetailsService(LoginAttemptService loginAttempts){
		return new CustomUserDetailsService(loginAttempts);
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
