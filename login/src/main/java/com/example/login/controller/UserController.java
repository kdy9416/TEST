package com.example.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.service.LoginService;

@RestController
public class UserController {

	@Autowired
	LoginService service;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
