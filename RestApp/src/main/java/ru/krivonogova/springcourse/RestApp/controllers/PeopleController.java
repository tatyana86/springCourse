package ru.krivonogova.springcourse.RestApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.krivonogova.springcourse.RestApp.models.Person;
import ru.krivonogova.springcourse.RestApp.services.PeopleService;
import ru.krivonogova.springcourse.RestApp.util.PersonErrorResponse;
import ru.krivonogova.springcourse.RestApp.util.PersonNotCreatedException;
import ru.krivonogova.springcourse.RestApp.util.PersonNotFoundException;

@RestController
@RequestMapping("/people")
public class PeopleController {

	private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<Person> getPeople() {
        return peopleService.findAll(); // Jackson конвертирует эти объекты в JSON
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
    	// статус 200
        return peopleService.findOne(id); // Jackson конвертирует в JSON
    }
    
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person,
    										BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		StringBuilder errorMsg = new StringBuilder();
    		
    		List<FieldError> errors = bindingResult.getFieldErrors();
    		
    		for(FieldError error : errors) {
    			errorMsg.append(error.getField())
    					.append(" - ")
    					.append(error.getDefaultMessage())
    					.append(";");
    		}
    		
    		throw new PersonNotCreatedException(errorMsg.toString());
    	}
    	
    	peopleService.save(person);
    	
    	return ResponseEntity.ok(HttpStatus.OK);
    }
    
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotFoundException e) {
    	PersonErrorResponse response = new PersonErrorResponse(
    			"Person with this id wasn't found", 
    			System.currentTimeMillis()
    	);
    	
    	return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // статус 404
    }
    
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotCreatedException e) {
    	PersonErrorResponse response = new PersonErrorResponse(
    			e.getMessage(), 
    			System.currentTimeMillis()
    	);
    	
    	return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // статус 400
    }
}



















