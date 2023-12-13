package ru.krivonogova.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Person {
    private int id;
    
	@NotEmpty(message = "Name should not be empty")
	@Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\\\w+", message =  "TODO")
    private String name;
	
	@Min(value = 1910, message = "TODO")
	@Pattern(regexp = "\\d{4}", message =  "TODO")
    private int age;
	
    
    public Person() {
	}

    public Person(int id, String name, int age) {
		this.id = id;
		this.name = name;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
