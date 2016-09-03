package com.alex.alexwebapp.controllers;

import com.auth0.spring.security.mvc.Auth0CallbackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Runner on 2016/09/02.
 */
@Controller
public class Auth0CallbackController extends Auth0CallbackHandler {

    private static final Logger logger = LoggerFactory.getLogger(UiController.class);

    @RequestMapping(value = "${auth0.loginCallback}", method = RequestMethod.GET)
    protected void callback(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        super.handle(req, res);
    }
}
