package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SecuredService {

	@PreAuthorize("hasRole('USER')")
	public List<String> sayHelloSecured() {
		return Arrays.asList("Hello", "World", "from", "Private");
	}
}
