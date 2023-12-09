package ru.krivonogova.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.krivonogova.springcourse.dao.PersonDAO;
import ru.krivonogova.springcourse.models.Person;

@Component
public class PersonValidator implements Validator{
	
	private final PersonDAO personDAO;
	
	@Autowired
	public PersonValidator(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Person person = (Person) o;
		
		// есть ли человек с таким email в БД?
		if(personDAO.show(person.getEmail()).isPresent()) {
			errors.rejectValue("email", "", "This email is already exist");
		}
	}
}














