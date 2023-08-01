package com.contact.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.contact.web.entities.User;


public interface UserRepo extends JpaRepository<User, Integer>{
	
//	@Query("select u from User u where u.email= :email")
	public User findByEmail(@Param(value = "email") String email);
	
}
