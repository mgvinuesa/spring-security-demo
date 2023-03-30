package com.example.demo.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecuredService {

	@PreAuthorize("hasRole('ADMIN')")
	public void invoke() {
		log.info("Invoke Secured Service because is ADMIN");
	}

}
