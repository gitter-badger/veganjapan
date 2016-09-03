package com.alex.alexwebapp;

import com.auth0.spring.security.mvc.Auth0Config;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Created by Runner on 2016/09/01.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
public class MyAuth0Config extends Auth0Config {

    @Override
    protected void authorizeRequests(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(securedRoute).authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin().loginPage("/login");
    }
}
