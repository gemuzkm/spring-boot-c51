package com.example.springbootc51.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.List;

public class User {

//	@NotBlank // " " true
//	@NotEmpty //"    " false

	private long id;

	@NotEmpty(message = "name empty")
	@Size(min = 3, max = 50, message = "name should be between 3 and 50 characters")
	private String name;

	@NotEmpty(message = "password empty")
	@Size(min = 3, max = 50, message = "password should be between 3 and 50 characters")
	private String password;

	@NotEmpty(message = "email empty")
	@Email(message = "not valid email")
	private String email;

	private List<Operation> operationList;

	public User() {
	}

	public User(long id, String name, String password, String email) {
		this.id = id;
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

	public List<Operation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Operation> operationList) {
		this.operationList = operationList;
	}
}
