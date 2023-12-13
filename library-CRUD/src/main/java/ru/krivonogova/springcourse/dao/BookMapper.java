package ru.krivonogova.springcourse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.krivonogova.springcourse.models.Book;

public class BookMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet resultSet, int i) throws SQLException {
		Book book = new Book();
		
		book.setId(resultSet.getInt("id"));
		book.setAuthor(resultSet.getString("author"));
		book.setAge(resultSet.getInt("age"));
		
		return book;
	}

}
