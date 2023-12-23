package ru.krivonogova.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.krivonogova.springcourse.models.Person;
import ru.krivonogova.springcourse.services.PeopleService;

@Component
public class PersonValidator implements Validator{
	
//	private final PersonDAO personDAO;
	private final PeopleService peopleService;
	
	@Autowired
	public PersonValidator(PeopleService peopleService) {
		this.peopleService = peopleService;
	}
	
//	@Autowired
//	public PersonValidator(PersonDAO personDAO) {
//		this.personDAO = personDAO;
//	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}



	@Override
	public void validate(Object o, Errors errors) {
		Person person = (Person) o;
		
		// есть ли человек с таким именем в БД?
		if(peopleService.findOne(person.getName()).isPresent()) {
			errors.rejectValue("name", "", "The person with such name is already exist");
		}
	}
}














