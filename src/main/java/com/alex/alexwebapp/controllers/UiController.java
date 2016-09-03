package com.alex.alexwebapp.controllers;

import com.alex.alexwebapp.MyAuth0Config;
import com.auth0.Auth0User;
import com.auth0.NonceUtils;
import com.auth0.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
public class UiController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final Logger logger = LoggerFactory.getLogger(UiController.class);

	@Autowired
	private MyAuth0Config myAuth0Config;

	@Value("${google.browser-key}")
	private String googleBrowserKey;

	@Value("${auth0.clientId}")
	private String clientId;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("googleBrowserKey", googleBrowserKey);
		return "index";
	}

	@RequestMapping("hello")
	public String hello(Model model) {
		model.addAttribute("hello", jdbcTemplate.queryForObject("SELECT 1", String.class));
		model.addAttribute("helloText", "hello from ui controller");
		return "hello";
	}

	@RequestMapping(value="/login", method = RequestMethod.GET)
	protected String login(final Map<String, Object> model, final HttpServletRequest req) {
		NonceUtils.addNonceToStorage(req);
		model.put("clientId", myAuth0Config.getClientId());
		model.put("domain", myAuth0Config.getDomain());
		model.put("state", SessionUtils.getState(req));
		model.put("redirectUrl", new String(req.getRequestURL()).replace(req.getRequestURI(), "") +
				myAuth0Config.getLoginCallback());

		model.put("error", model.get("error") != null); // FIXME: do we need this?
		return "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	protected String logout(final HttpServletRequest request) {
		if (request.getSession() != null) {
			logger.debug("invalidating sesion");
			request.getSession().invalidate();
		}
		final String logoutPath = myAuth0Config.getOnLogoutRedirectTo();
		return "redirect:" + logoutPath;
	}

	@RequestMapping(value="/secure", method = RequestMethod.GET)
	protected String secure(final Map<String, Object> model, final HttpServletRequest req, final Principal principal) {
		logger.info("Secure page");
		final String name = principal.getName();
		logger.info("Principal name: " + name);
		final Auth0User user = SessionUtils.getAuth0User(req);
		logger.debug(user.toString());
		model.put("user", user);
		return "secure-page";
	}
}