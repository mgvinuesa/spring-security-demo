package com.example.demo.config.login;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.servlet.LocaleResolver;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private MessageSource messages;

	private LocaleResolver localeResolver;

	public CustomAuthenticationFailureHandler(LocaleResolver localeResolver, MessageSource messages) {
		this.localeResolver = localeResolver;
		this.messages = messages;
	}

	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException exception) throws IOException, ServletException {
		setDefaultFailureUrl("/login?error=true");

		super.onAuthenticationFailure(request, response, exception);

		final Locale locale = localeResolver.resolveLocale(request);

		String errorMessage = messages.getMessage("message.badCredentials", null, locale);

		if (exception instanceof LockedException) {
			errorMessage = messages.getMessage("auth.message.blocked", null, locale);
		}
		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
	}
}