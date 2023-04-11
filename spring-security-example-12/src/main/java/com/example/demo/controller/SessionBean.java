package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;

public class SessionBean implements InitializingBean {

	private String value;

	@Override
	public void afterPropertiesSet() throws Exception {
		value = UUID.randomUUID().toString();
	}

	public String getValue() {
		return value;
	}
}
