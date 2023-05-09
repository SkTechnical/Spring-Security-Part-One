package com.example.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
	
	@GetMapping("/public")
	@ResponseBody
	public String publicMethod() {
		return "Hello Public";
	}
	
	@GetMapping("/customer")
	@ResponseBody
	public String customerMethod() {
		return "Hello Customer";
	} 
	
	@GetMapping("/admin")
	@ResponseBody
	public String adminMethod() {
		return "Hello admin";
	}
	

}
