package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SecuredService;

@RestController
public class SecuredController {

	private SecuredService securedService;

	public SecuredController(@Autowired(required = false) SecuredService securedService) {
		this.securedService = securedService;
	}

	@GetMapping("/public/hello")
	public List<String> publicHello() {
		return Arrays.asList("Hello", "World", "from", "Public");
	}

	@GetMapping("/private/hello")
	public List<String> privateHello() {
		return Arrays.asList("Hello", "World", "from", "Private");
	}

	@GetMapping("/private/hello-method")
	public List<String> privateHelloWithMethod() {
		return this.securedService.sayHelloSecured();
	}

}
