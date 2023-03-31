package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.example.demo.cart.CartStore;

@Configuration
public class ApplicationConfig {

	/**
	 * If you want to inject (for example) an HTTP request scoped bean into another
	 * bean, you must inject an AOP proxy in place of the scoped bean.
	 * 
	 * @return
	 */
	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CartStore cartStore() {
		return new CartStore();
	}
}
