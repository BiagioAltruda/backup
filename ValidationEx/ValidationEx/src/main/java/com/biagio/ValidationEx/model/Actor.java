package com.biagio.ValidationEx.model;

import jakarta.validation.constraints.*;

public class Actor {

	@NotNull(message="Id cannot be null")
	@Positive(message="Id cannot be negative")
	private int id;
	@NotBlank(message="Name cannot be blank")
	private String firstName;
	@NotBlank(message="Last name cannot be blank")
	private String lastName;
	@NotNull(message="Age cannot be null")
	@Positive(message="Age cannot be negative")
	private int age;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
