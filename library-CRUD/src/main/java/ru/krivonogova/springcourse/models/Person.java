package ru.krivonogova.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Person {
    private int id;
    
	@NotEmpty()
	//@Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message =  "TODO")
	@Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message =  "Введите имя в формате: Фамилия Имя Отчество")

    private String name;
	
	@Min(value = 1910, message = "Год рождения должен быть больше 1910")
	@Max(value = 2016, message = "Разрешается регестрировать пользователей, старше 7 лет!")
    private int yearOfBirth;
	
    
    public Person() {
	}

    public Person(int id, String name, int yearOfBirth) {
		this.id = id;
		this.name = name;
		this.yearOfBirth = yearOfBirth;
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

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
}
