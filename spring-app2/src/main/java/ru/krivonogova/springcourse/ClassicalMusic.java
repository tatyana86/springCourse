package ru.krivonogova.springcourse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ClassicalMusic implements Music {
	private List<String> songs = new ArrayList<>();
	
	{
		songs.add("Hungarian Rhapsody");
		songs.add("The Love for Three Oranges Suite");
		songs.add("The Tale of Tsar Saltan");
	}
	
	@Override
	public List<String> getSongs() {
		return songs;
	}
}
