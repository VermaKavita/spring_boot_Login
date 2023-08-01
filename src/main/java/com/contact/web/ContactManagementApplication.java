package com.contact.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class ContactManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactManagementApplication.class, args);
	}

}
