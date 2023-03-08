package com.example.demo.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	@PreAuthorize("hasAuthority('APPROLE_Admin')")
	public String hello(Principal user, Authentication authentication) {
		return "Hello " + user.getName();
	}
}
