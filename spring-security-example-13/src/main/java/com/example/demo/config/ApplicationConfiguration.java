package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.withUsername("Manuel").password(NoOpPasswordEncoder.getInstance().encode("12345"))
				.roles("USER").build();

		UserDetails user2 = User.withUsername("Jose").password(NoOpPasswordEncoder.getInstance().encode("12345"))
				.roles("STAFF").build();

		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager(user1, user2);
		return userDetailsService;
	}
}
