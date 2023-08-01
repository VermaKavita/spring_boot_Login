package com.contact.web.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.contact.web.entities.User;
import com.contact.web.service.UserService;

@Controller
public class BaseController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String home(Model m) {
		m.addAttribute("title", "Contact Manager");
		return "home";
	}

	@GetMapping(value="/about")
	public String showContact(Model m) {
		m.addAttribute("title", "About - Contact Manager");
		return "about";
	}

	@GetMapping("/signin")
	public String showLogin(Model m) {
		m.addAttribute("title", "Login Page - Contact Manager");
		return "login";
	}

	/*
	 * @PostMapping("/login") public String processLogin(@RequestParam("username")
	 * String username, @RequestParam("pwd") String pwd,Model model,Principal
	 * principal) { System.out.println("user naem is "+username+"  "
	 * +"password is "+pwd); User user = (User)
	 * userService.loadUserByUsername(username);
	 * model.addAttribute(principal.getName()); return "/user/index"; }
	 */
	
	@GetMapping("/signup")
	public String signUpPage(Model m) {
		m.addAttribute("title", "Register - Contact Manager");
		m.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/do_register")
	public String registerUser(@Valid User user, BindingResult errors,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m,
			RedirectAttributes redirAttrs) {
		try {
			if (!agreement) {
				System.out.println(agreement);
				throw new Exception("You haven't agreed terms and conditions..!");
//				redirAttrs.addFlashAttribute("error", "You haven't agreed terms & conditions.");
			}
			if (errors.hasErrors()) {
				return "signup";
			}
			this.userService.save(user);
			m.addAttribute("user", new User());
			redirAttrs.addFlashAttribute("success", "Registered successfully.");
			return "redirect:/signup";
		} catch (Exception e) {
			m.addAttribute("user", user);
			redirAttrs.addFlashAttribute("error", "Something went wrong.");
			e.printStackTrace();
			System.out.println("exception block");
			return "redirect:/signup";
		}

	}
	

}
