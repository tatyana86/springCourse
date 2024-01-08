package ru.krivonogova.springcourse.RestApp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import ru.krivonogova.springcourse.RestApp.dto.PersonDTO;
import ru.krivonogova.springcourse.RestApp.models.Person;
import ru.krivonogova.springcourse.RestApp.services.PeopleService;
import ru.krivonogova.springcourse.RestApp.util.PersonErrorResponse;
import ru.krivonogova.springcourse.RestApp.util.PersonNotCreatedException;
import ru.krivonogova.springcourse.RestApp.util.PersonNotFoundException;

@RestController
@RequestMapping("/people")
public class PeopleController {

	private final PeopleService peopleService;
	private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
		this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<PersonDTO> getPeople() {
        return peopleService.findAll().stream().map(this::convertToPersonDTO)
        		.collect(Collectors.toList()); // Jackson конвертирует эти объекты в JSON
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id) {
    	// статус 200
        return convertToPersonDTO(peopleService.findOne(id)); // Jackson конвертирует в JSON
    }
    
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,
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
    	
    	peopleService.save(convertToPerson(personDTO));
    	
    	return ResponseEntity.ok(HttpStatus.OK);
    }
    
    private Person convertToPerson(PersonDTO personDTO) {
//		Person person = new Person();
//		
//		person.setName(personDTO.getName());
//		person.setAge(personDTO.getAge());
//		person.setEmail(personDTO.getEmail());
    	
//    	ModelMapper modelMapper = new ModelMapper();
    	
    	Person person = modelMapper.map(personDTO, Person.class);

		return person;
	}
    
    private PersonDTO convertToPersonDTO(Person person) {
    	return modelMapper.map(person, PersonDTO.class);
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



















