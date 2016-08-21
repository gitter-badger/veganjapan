package com.alex.alexwebapp.controllers;

import com.alex.alexwebapp.models.User;
import com.alex.alexwebapp.models.UserDao;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UiController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private UserDao userDao;

	private static final Logger logger = LoggerFactory.getLogger(UiController.class);

	@Autowired
	private Environment environment;

	@Value("${aproperty}")
	String property;

	@Value("${aproperty2}")
	String property2;

	@RequestMapping("hello")
	public String hello(Model model) {
		model.addAttribute("hello", jdbcTemplate.queryForObject("SELECT 1", String.class));
		model.addAttribute("helloText", "hello from ui controller");
		logger.info(property);
		logger.info(property2);
		logger.info("{} {}", environment.getActiveProfiles()[0], environment.getActiveProfiles().length);
		return "hello";
	}

	/**
	 * /get-by-email  --> Return the id for the user having the passed email.
	 *
	 * @param email The email to search in the database.
	 * @return The user id or a message error if the user is not found.
	 */
	@RequestMapping("/get-user-by-email")
	@ResponseBody
	public String getByEmail(String email) {
		String userId;
		try {
			User user = userDao.findByEmail(email);
			userId = String.valueOf(user.getId());
		}
		catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId;
	}
}