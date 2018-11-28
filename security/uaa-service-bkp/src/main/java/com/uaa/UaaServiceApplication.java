package com.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

@SpringBootApplication
@EnableEurekaClient
public class UaaServiceApplication extends AuthorizationServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(UaaServiceApplication.class, args);
	}
}
