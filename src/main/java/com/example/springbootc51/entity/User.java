package com.example.springbootc51.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "name empty")
	@Size(min = 3, max = 50, message = "name should be between 3 and 50 characters")
	private String name;

	@NotNull(message = "password empty")
	@Size(min = 3, max = 50, message = "password should be between 3 and 50 characters")
	private String password;

	@NotNull(message = "email empty")
	@Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "not valid email")
	private String email;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Operation> operationList;

	public User(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public void setOperationList(List<Operation> operationList) {
		if (operationList != null) {
			operationList.forEach(o -> o.setUser(this));
		}
		this.operationList = operationList;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}