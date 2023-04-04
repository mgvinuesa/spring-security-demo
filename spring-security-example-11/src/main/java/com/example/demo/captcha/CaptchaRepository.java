package com.example.demo.captcha;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.apiclub.captcha.Captcha;

@Component
public class CaptchaRepository {

	private Map<String, Captcha> captchaStore = new HashMap<>();;

	public void saveCaptchaForSession(String idSession, Captcha captcha) {
		this.captchaStore.put(idSession, captcha);
	}

	public Captcha getCaptcha(String idSession) {
		return this.captchaStore.get(idSession);
	}

}
