package ru.krivonogova.springcourse.models;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Book")
public class Book {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@ManyToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
	
	@Column(name = "takedAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date takedAt;
	
	@Transient
	private boolean isOverdue;
    
	@Column(name = "title")
    private String title;
    
	@Column(name = "author")
	@NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов длиной")
    private String author;
	
	@Column(name = "year")
    @Min(value = 1500, message = "Год должен быть больше, чем 1500")
    private int year;
	
    public Book() {
	}

    public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}

	public Date getTakedAt() {
		return takedAt;
	}

	public void setTakedAt(Date takedAt) {
		this.takedAt = takedAt;
	}

	public boolean isOverdue() {
		return isOverdue;
	}

	public void setOverdue(boolean isOverdue) {
		this.isOverdue = isOverdue;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
