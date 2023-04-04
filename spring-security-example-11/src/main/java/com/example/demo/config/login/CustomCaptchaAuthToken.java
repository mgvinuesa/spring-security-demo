package com.example.demo.config.login;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomCaptchaAuthToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7099640289556224165L;

	private String captcha;
	private String sessionId;

	public CustomCaptchaAuthToken(Object principal, Object credentials, String captcha, String sessionId) {
		super(principal, credentials);
		this.captcha = captcha;
		this.sessionId = sessionId;
		super.setAuthenticated(false);
	}

	public CustomCaptchaAuthToken(Object principal, Object credentials, String captcha, String sessionId,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		this.captcha = captcha;
		this.sessionId = sessionId;
		super.setAuthenticated(true); // must use super, as we override
	}

	public String getCaptcha() {
		return this.captcha;
	}

	public String getSessionId() {
		return sessionId;
	}
}
