package com.alex.alexwebapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

	@RequestMapping("hello")
	public String hello() {
		return "hello from api";
	}
}