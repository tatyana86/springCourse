package ru.krivonogova.springcourse.hibernate_app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Passport")
public class Passport implements Serializable {

	@Id
	@OneToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person person;
	
	@Column(name = "passport_number")
	private int passportNumber;
	
	public Passport() {}

	public Passport(int passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(int passportNumber) {
		this.passportNumber = passportNumber;
	}

	@Override
	public String toString() {
		return "Passport [person=" + person + ", passportNumber=" + passportNumber + "]";
	}
	
}









