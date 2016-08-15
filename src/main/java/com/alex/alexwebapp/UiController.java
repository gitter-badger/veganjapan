package com.alex.alexwebapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Controller
public class UiController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping("hello")
	public String hello(Model model) {
		model.addAttribute("hello", jdbcTemplate.queryForObject("SELECT col FROM random_table WHERE id = 1", String.class));
		return "hello";
	}
}