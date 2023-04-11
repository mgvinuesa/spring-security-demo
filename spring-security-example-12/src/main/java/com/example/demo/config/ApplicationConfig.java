package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.controller.SessionBean;

@Configuration
public class ApplicationConfig {

	@Bean
	@SessionScope
	public SessionBean sessionBean() {
		return new SessionBean();
	}
}
