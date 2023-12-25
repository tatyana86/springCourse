package ru.krivonogova.springcourse.services;

import java.util.Date;
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
		
		List<Book> books = person.get().getBooks();
		
		if(person.isPresent()) {
			Hibernate.initialize(person.get().getBooks());
			
			for(Book book : books) {
				long timeOfDiffer = Math.abs(book.getTakedAt().getTime() - new Date().getTime());
				long MILSEC_IN_10_DAYS = 10 * 24 * 3600 * 1000;
				if(timeOfDiffer > MILSEC_IN_10_DAYS) {
					book.setOverdue(true);
				}
			}
		}
		
		return books;
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
