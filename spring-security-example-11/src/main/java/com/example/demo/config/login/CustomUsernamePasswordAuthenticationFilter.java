package com.example.demo.config.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final String INPUT_CAPTCHA = "inputCaptcha";

	public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		CustomCaptchaAuthToken authRequest = getAuthRequest(request);
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private CustomCaptchaAuthToken getAuthRequest(HttpServletRequest request) {
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		String captcha = obtainCaptcha(request);
		String sessionId = obtainSessionId(request);
		if (username == null) {
			username = "";
		}
		if (password == null) {
			password = "";
		}
		if (captcha == null) {
			captcha = "";
		}
		if (sessionId == null) {
			sessionId = "";
		}
		return new CustomCaptchaAuthToken(username, password, captcha, sessionId);
	}

	private String obtainCaptcha(HttpServletRequest request) {
		return request.getParameter(INPUT_CAPTCHA);
	}

	private String obtainSessionId(HttpServletRequest request) {
		return request.getSession().getId();
	}
}
