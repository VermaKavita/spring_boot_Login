package com.contact.web.repo;

import org.springframework.data.repository.CrudRepository;

import com.contact.web.entities.Contact;

public interface Contactrepo extends CrudRepository<Contact,Integer> {

	
}
