package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
/*
 * With Spring Security on the classpath, Spring Boot Security
 * Auto-Configuration‘s WebSecurityEnablerConfiguration
 * activates @EnableWebSecurity for us.
 */
public class SecurityConfig {

	/*
	 * https://spring.io/blog/2022/02/21/spring-security-without-the-
	 * websecurityconfigureradapter
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated()).httpBasic();
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer ignoreResources() {
		return (webSecurity) -> webSecurity.ignoring().requestMatchers("/public");
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

		UserDetails testUser = User.withUsername("manu").password(encoder.encode("1234")).authorities("read").build();
		userDetailsManager.createUser(testUser);

		return userDetailsManager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(6);
	}
}
