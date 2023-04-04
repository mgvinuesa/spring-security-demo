package com.example.demo.captcha;

import org.springframework.stereotype.Service;

import cn.apiclub.captcha.Captcha;

@Service
public class CaptchaService {

	private CaptchaRepository captchaRepository;

	public CaptchaService(CaptchaRepository captchaRepository) {
		this.captchaRepository = captchaRepository;
	}

	public String createCaptcha(String idSession, int i, int j) {
		Captcha captcha = CaptchaFactory.createCaptcha(i, j);
		this.captchaRepository.saveCaptchaForSession(idSession, captcha);
		return CaptchaFactory.encodeCaptcha(captcha);
	}

	public boolean isCaptchaValid(String idSession, String captcha) {
		Captcha loadedCaptcha = this.captchaRepository.getCaptcha(idSession);
		return loadedCaptcha != null && captcha.equals(loadedCaptcha.getAnswer());
	}

}
