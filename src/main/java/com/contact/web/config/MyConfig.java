package com.contact.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/about").setViewName("about");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/signup").setViewName("signup");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/user/index").setViewName("user_dashboard");}
	
	
}
