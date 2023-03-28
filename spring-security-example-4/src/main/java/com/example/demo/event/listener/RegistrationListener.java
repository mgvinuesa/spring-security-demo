package com.example.demo.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.demo.event.OnRegistrationCompleteEvent;
import com.example.demo.user.UserService;
import com.example.demo.user.persistence.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	@Autowired
	private UserService service;

	@Autowired
	private MessageSource messages;

	@Autowired
	private Environment env;

	@Override
	public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(final OnRegistrationCompleteEvent event) {
		final User user = event.getUser();
		final String token = UUID.randomUUID().toString();
		service.createVerificationTokenForUser(user, token);

		final String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
		// Send email
		log.info("Confirm registration in the link: {}", confirmationUrl);

	}

}
