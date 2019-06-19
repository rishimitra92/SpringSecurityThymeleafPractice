package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@GetMapping("/showLoginForm")
	public String showLoginForm() {
		return "login-form";
	}
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "access-denied";
	}

}
