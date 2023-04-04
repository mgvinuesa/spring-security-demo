package com.example.demo.config.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

	private static Integer MAX_ATTEMPTS = 3;

	private Map<String, Integer> attempts = new HashMap<>();

	public void addFailureAttempt(String userName) {
		Integer attempt = attempts.get(userName);
		if (attempt == null) {
			attempt = 1;
		} else {
			attempt++;
		}
		attempts.put(userName, attempt);
	}

	public boolean isBlocked(String userName) {
		return attempts.get(userName) != null && MAX_ATTEMPTS <= attempts.get(userName);
	}

}
