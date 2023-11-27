package ru.krivonogova.springcourse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RockMusic implements Music {
	private List<String> songs = new ArrayList<>();
	
	{
		songs.add("Wind cries Mary");
		songs.add("Smells Like Teen Spirit");
		songs.add("Paranoid");
	}
	
	@Override
	public List<String> getSongs() {
		return songs;
	}
}