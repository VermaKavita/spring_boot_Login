package com.contact.web.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.contact.web.ContactManagementApplication;
import com.contact.web.entities.Role;
import com.contact.web.entities.User;
import com.contact.web.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	 Logger logger = LoggerFactory.getLogger(ContactManagementApplication.class);
	
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	 
	@Autowired
	private UserRepo userRepo;
	@Override
	public User save(User user) {
	user.setRole(Arrays.asList(new Role("ROLE_USER")));
	user.setEnabled(true);
	user.setPwd(passwordEncoder.encode(user.getPwd())); 
		return userRepo.save(user);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("Invalid username and Password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPwd(),mapRolesToAuthoroties(user.getRole()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthoroties(Collection<Role> roles){
		return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
	}
	
}
