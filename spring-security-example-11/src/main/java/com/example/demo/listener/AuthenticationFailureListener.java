package com.example.demo.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.example.demo.config.login.LoginAttemptService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	private LoginAttemptService loginAttempt;

	public AuthenticationFailureListener(LoginAttemptService loginAttempt) {
		this.loginAttempt = loginAttempt;
	}

	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		log.info("Error trying to login with principal {}", event.getAuthentication().getPrincipal());
		this.loginAttempt.addFailureAttempt((String) event.getAuthentication().getPrincipal());
	}

}