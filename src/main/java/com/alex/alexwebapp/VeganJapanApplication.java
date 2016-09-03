package com.alex.alexwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ComponentScan(basePackages = {"com.alex.alexwebapp", "com.auth0.spring.security.mvc"})
@PropertySources({
		@PropertySource("classpath:auth0.properties")
})
public class VeganJapanApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeganJapanApplication.class, args);
	}
}
