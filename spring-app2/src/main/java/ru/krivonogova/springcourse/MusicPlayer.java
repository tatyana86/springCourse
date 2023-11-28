package ru.krivonogova.springcourse;

import java.util.List;
import java.util.Random;

public class MusicPlayer {
	
	private List<Music> musicList;
	
    public MusicPlayer(List<Music> musicList) {
		this.musicList = musicList;
	}
    
    public String playMusic() {
    	Random random = new Random();
    	int number = random.nextInt(musicList.size());
    	
    	return musicList.get(number).getSong();
    }

}










