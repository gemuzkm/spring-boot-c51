package com.example.springbootc51.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

	private static long index = 1;
	private long id;

	@NotNull(message = "name empty")
	@Size(min = 3, max = 50, message = "name should be between 3 and 50 characters")
	private String name;

	@NotNull(message = "password empty")
	@Size(min = 3, max = 50, message = "password should be between 3 and 50 characters")
	private String password;

	@NotNull(message = "email empty")
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$", message = "not valid email")
	private String email;

	public User() {
	}

	public User(String name, String password, String email) {
		id = index;
		index++;
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
