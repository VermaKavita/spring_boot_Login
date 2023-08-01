package com.contact.web.service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.contact.web.entities.User;

public interface UserService extends UserDetailsService{

	User save(User user);
}
