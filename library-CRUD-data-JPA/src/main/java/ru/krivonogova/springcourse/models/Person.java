package ru.krivonogova.springcourse.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Person")
public class Person {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@OneToMany(mappedBy = "owner")
	private List<Book> books;
	
	@Column(name = "name")
	@NotEmpty()
	//@Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message =  "TODO")
	@Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message =  "Введите имя в формате: Фамилия Имя Отчество")
    private String name;
	
	@Column(name = "yearOfBirth")
	@Min(value = 1910, message = "Год рождения должен быть больше 1910")
	@Max(value = 2016, message = "Разрешается регестрировать пользователей, старше 7 лет!")
    private int yearOfBirth;
	
	
    
    public Person() {
	}

    public Person(String name, int yearOfBirth) {
		this.name = name;
		this.yearOfBirth = yearOfBirth;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
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
