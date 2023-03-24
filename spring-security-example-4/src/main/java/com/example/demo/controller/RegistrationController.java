package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.user.CustomUserService;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.error.UserAlreadyExistException;
import com.example.demo.user.persistence.User;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RegistrationController {

	@Autowired
	private CustomUserService userService;

	@Autowired
	private MessageSource messages;

	@GetMapping("/user/registration")
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "registration";
	}

	@PostMapping("/user/registration")
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, HttpServletRequest request,
			Errors errors) {
		try {
			final User registered = userService.registerNewUserAccount(userDto);
		} catch (final UserAlreadyExistException uaeEx) {
			ModelAndView mav = new ModelAndView("registration", "user", userDto);
			String errMessage = messages.getMessage("message.regError", null, request.getLocale());
			mav.addObject("message", errMessage);
			return mav;
		} catch (final RuntimeException ex) {
			log.warn("Unable to register user", ex);
			return new ModelAndView("emailError", "user", userDto);
		}
		return new ModelAndView("successRegister", "user", userDto);
	}
}
