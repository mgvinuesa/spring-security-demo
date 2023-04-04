package com.example.demo.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureLockedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationLockListener implements ApplicationListener<AuthenticationFailureLockedEvent> {

	@Override
	public void onApplicationEvent(AuthenticationFailureLockedEvent event) {
		log.info("User principal {} is locked", event.getAuthentication().getPrincipal());
	}

}