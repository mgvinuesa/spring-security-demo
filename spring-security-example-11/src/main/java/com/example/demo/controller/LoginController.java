package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.captcha.CaptchaService;

@Controller
public class LoginController {

	private CaptchaService captchaService;

	public LoginController(CaptchaService captchaService) {
		this.captchaService = captchaService;
	}

	@GetMapping("/login")
	ModelAndView login(HttpSession session) {
		String captcha = captchaService.createCaptcha(session.getId(), 240, 70);
		return new ModelAndView("login", "captcha", captcha);
	}

}
