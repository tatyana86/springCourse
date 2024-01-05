package ru.krivonogova.springcourse.securityapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Person")
public class Person {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Имя не должно быть пустым")
	@Size(min = 2, max = 100, message = "От 2 до 100 символов")
	@Column(name = "username")
	private String username;
	
	@Min(value = 1900, message = "Больше, чем 1900")
	@Column(name = "year_of_birth")
	private int yearOfBirth;
	
	@Column(name = "password")
	private String password;
	
	public Person() {}

	public Person(
			@NotEmpty(message = "Имя не должно быть пустым") @Size(min = 2, max = 100, message = "От 2 до 100 символов") String username,
			@Min(value = 1900, message = "Больше, чем 1900") int yearOfBirth) {
		super();
		this.username = username;
		this.yearOfBirth = yearOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + ", yearOfBirth=" + yearOfBirth + ", password=" + password
				+ "]";
	}
	
	
}
