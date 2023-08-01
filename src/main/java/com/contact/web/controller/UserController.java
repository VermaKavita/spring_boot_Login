package com.contact.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contact.web.entities.User;
import com.contact.web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/index")
	public String dashboard(Model m,User user ) {
		
		String email=user.getName();
		User user1=(User) userService.loadUserByUsername(email);
		System.out.println(user);
		return "/normal/user_dashboard";
	}
}
