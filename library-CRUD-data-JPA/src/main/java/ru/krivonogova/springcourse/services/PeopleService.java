package ru.krivonogova.springcourse.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.krivonogova.springcourse.models.Book;
import ru.krivonogova.springcourse.models.Person;
import ru.krivonogova.springcourse.repositories.BooksRepository;
import ru.krivonogova.springcourse.repositories.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class PeopleService {
	
	private final PeopleRepository peopleRepository;
	
	@Autowired
	public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
		this.peopleRepository = peopleRepository;
	}
	
	public List<Person> findAll() {
		return peopleRepository.findAll();
	}
	
	public List<Book> getBooksById(int id) {
		Optional<Person> person = peopleRepository.findById(id);
		Hibernate.initialize(person.get().getBooks());
		return person.get().getBooks();
	}
	
	public Person findOne(int id) {
		Optional<Person> foundPerson = peopleRepository.findById(id);
		return foundPerson.orElse(null);
	}
	
	public Optional<Person> findOne(String name) {
		return peopleRepository.findByName(name);
	}
	
	@Transactional
	public void save(Person person) {
		peopleRepository.save(person);
	}
	
	@Transactional
	public void update(int id, Person updatedPerson) {
		updatedPerson.setId(id);
		peopleRepository.save(updatedPerson);
	}
	
	@Transactional
	public void delete(int id) {
		peopleRepository.deleteById(id);
	}

}
