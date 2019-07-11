package com.santg.disproclientes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm() {
		
		return "login";
	}
	
	// add request mapping for /accessDenied
	@GetMapping("/accessDenied")
	public String accessDenied() {
		
		return "access-denied";
	}
}
