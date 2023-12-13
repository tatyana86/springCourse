package ru.krivonogova.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Book {
    private int id;
    
	@NotEmpty(message = "Name should not be empty")
    private String name;
    
	@NotEmpty(message = "Name should not be empty")
	@Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\\\w+", message =  "TODO")
    private String author;
	
	@Min(value = 1910, message = "TODO")
	@Pattern(regexp = "\\d{4}", message =  "TODO")
    private int age;
	
    
    public Book() {
	}

    public Book(int id, String name, String author, int age) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.age = age;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
