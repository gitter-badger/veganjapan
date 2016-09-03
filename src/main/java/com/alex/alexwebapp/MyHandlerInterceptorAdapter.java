package com.alex.alexwebapp;

import com.alex.alexwebapp.controllers.UiController;
import com.auth0.Auth0User;
import com.auth0.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Runner on 2016/09/03.
 * add objects to model for all requests
 */
@Component
public class MyHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

    @Autowired
    private MyAuth0Config myAuth0Config;

    private static final Logger logger = LoggerFactory.getLogger(UiController.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.debug("post-handling request");

        if (request.getUserPrincipal() != null) {
            logger.info("user {} is logged in", request.getUserPrincipal().getName());
        }
        if (request.getAttribute("auth0User") != null) {
            final Auth0User user = SessionUtils.getAuth0User(request);
            logger.info("user: {}", user.toString());
        }

        if (modelAndView != null) {

        }
    }

}
