package com.example.demo.config.login;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class CustomUserDetailsService extends InMemoryUserDetailsManager implements InitializingBean {

	private LoginAttemptService attemptsService;

	public CustomUserDetailsService(LoginAttemptService service) {
		this.attemptsService = service;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		boolean isBlocked = this.attemptsService.isBlocked(username);
		UserDetails user = super.loadUserByUsername(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, !isBlocked, user.getAuthorities());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		var user = User.withUsername("Manuel").password("12345").authorities("read").build();
		this.createUser(user);

	}
}
