package com.alex.alexwebapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UiController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final Logger logger = LoggerFactory.getLogger(UiController.class);

	@Autowired
	private Environment environment;

	@Value("${aproperty}")
	String property;

	@Value("${aproperty2}")
	String property2;

	@RequestMapping("hello")
	public String hello(Model model) {
		model.addAttribute("hello", jdbcTemplate.queryForObject("SELECT col FROM random_table WHERE id = 1", String.class));
		logger.info(property);
		logger.info(property2);
		logger.info("{} {}", environment.getActiveProfiles()[0], environment.getActiveProfiles().length);
		return "hello";
	}
}