package ru.krivonogova.springcourse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.krivonogova.springcourse.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer>{
	public List<Book> findByTitleStartingWith(String query);
}
