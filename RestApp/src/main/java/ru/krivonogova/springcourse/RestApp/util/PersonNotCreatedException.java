package ru.krivonogova.springcourse.RestApp.util;

public class PersonNotCreatedException extends RuntimeException {
	
	public PersonNotCreatedException(String msg) {
		super(msg);
	}

}
