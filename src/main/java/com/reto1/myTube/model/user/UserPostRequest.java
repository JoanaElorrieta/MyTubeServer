package com.reto1.myTube.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserPostRequest {

	@NotNull(message = "El id no puede ser Nulo")
	@NotBlank(message = "El id no puede ser Blanco")
	@NotEmpty(message = "El id no puede ser Vacio")
	private int id;
	
	@NotNull(message = "El nombre no puede ser Nulo")
	@NotBlank(message = "El nombre no puede ser Blanco")
	@NotEmpty(message = "El nombre no puede ser Vacio")
	private String name;
	
	@NotNull(message = "El apellido no puede ser Nulo")
	@NotBlank(message = "El apellido no puede ser Blanco")
	@NotEmpty(message = "El apellido no puede ser Vacio")
	private String lastName;
	
	@NotNull(message = "El email no puede ser Nulo")
	@NotBlank(message = "El email no puede ser Blanco")
	@NotEmpty(message = "El email no puede ser Vacio")
	private String email;
	
	@NotNull(message = "La password no puede ser Nulo")
	@NotBlank(message = "La password no puede ser Blanco")
	@NotEmpty(message = "La password no puede ser Vacio")
	private String password;
	
	public UserPostRequest() {}

	public UserPostRequest(
			@NotNull(message = "El id no puede ser Nulo")@NotBlank(message = "El id no puede ser Blanco")@NotEmpty(message = "El id no puede ser Vacio")int id, 
			@NotNull(message = "El nombre no puede ser Nulo")@NotBlank(message = "El nombre no puede ser Blanco")@NotEmpty(message = "El nombre no puede ser Vacio")String name,
			@NotNull(message = "El apellido no puede ser Nulo")@NotBlank(message = "El apellido no puede ser Blanco")@NotEmpty(message = "El apellido no puede ser Vacio")String lastName, 
			@NotNull(message = "El email no puede ser Nulo")@NotBlank(message = "El email no puede ser Blanco")@NotEmpty(message = "El email no puede ser Vacio")String email, 
			@NotNull(message = "La password no puede ser Nulo")@NotBlank(message = "La password no puede ser Blanco")@NotEmpty(message = "La password no puede ser Vacio")String password) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserPostRequest [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}
	
}
