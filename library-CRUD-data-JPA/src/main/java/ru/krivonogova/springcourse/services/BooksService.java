package ru.krivonogova.springcourse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.krivonogova.springcourse.models.Book;
import ru.krivonogova.springcourse.models.Person;
import ru.krivonogova.springcourse.repositories.BooksRepository;

@Service
@Transactional(readOnly = true)
public class BooksService {
	
	private final BooksRepository booksRepository;

	@Autowired
	public BooksService(BooksRepository booksRepository) {
		this.booksRepository = booksRepository;
	}
	
	public List<Book> findAll() {
		return booksRepository.findAll();
	}
	
	public Book findOne(int id) {
		Optional<Book> foundBook = booksRepository.findById(id);
		return foundBook.orElse(null);
	}
	
	@Transactional
	public void save(Book book) {
		booksRepository.save(book);
	}
	
	@Transactional
	public void update(int id, Book updatedBook) {
		updatedBook.setId(id);
		booksRepository.save(updatedBook);
	}
	
	@Transactional
	public void delete(int id) {
		booksRepository.deleteById(id);
	}
	

	// владелец книги
	public Person getOwner(int id) {
//		Optional<Book> findBook = booksRepository.findById(id);
//		return findBook.g
		return booksRepository.findById(id).map(Book::getOwner).orElse(null);
	}
	
    // выдача книги
	@Transactional
	public void assign(int id, Person selectedPerson) {
		booksRepository.getOne(id).setOwner(selectedPerson);
		booksRepository.save(booksRepository.getOne(id));
	}
	
    // возврат книги
	@Transactional
	public void release(int id) {
		booksRepository.getOne(id).setOwner(null);
		booksRepository.save(booksRepository.getOne(id));
	}
	
}
