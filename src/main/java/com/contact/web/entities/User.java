package com.contact.web.entities;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Component
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty(message = "Can't be blank.")
	@Size(min = 4, max = 20, message = "Min 2 & Max 20 character allowed.")
	private String name;
	@Column(length = 500)
	private String about;
	@NotEmpty
	private String email;
	@NotEmpty
	private String pwd;
	private String image;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> role;
	private boolean enabled;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Contact> contacts = new ArrayList<>();

	public List<Contact> getContacts() {
		return contacts;
	}

	public User(int id,
			@NotEmpty(message = "Can't be blank.") @Size(min = 4, max = 20, message = "Min 2 & Max 20 character allowed.") String name,
			String about, @NotEmpty String email, @NotEmpty String pwd, String image, Collection<Role> role,
			boolean enabled, List<Contact> contacts) {
		super();
		this.id = id;
		this.name = name;
		this.about = about;
		this.email = email;
		this.pwd = pwd;
		this.image = image;
		this.role = role;
		this.enabled = enabled;
		this.contacts = contacts;
	}

	public User() {
		super();

	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Collection<Role> getRole() {
		return role;
	}

	public void setRole(Collection<Role> role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
