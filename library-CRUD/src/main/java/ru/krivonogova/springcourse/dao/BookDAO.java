package ru.krivonogova.springcourse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.krivonogova.springcourse.models.Book;

@Component
public class BookDAO {

private final JdbcTemplate jdbcTemplate;
	
	@Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
    	return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }
    
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, autor, age) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getAge());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, age=? WHERE id=?", updatedBook.getName(), updatedBook.getAuthor(),
                updatedBook.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
}
