package ru.krivonogova.springcourse;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {
    private ClassicalMusic classicalMusic;
    private RockMusic rockMusic;
	
    @Autowired
    public MusicPlayer(ClassicalMusic classicalMusic, RockMusic rockMusic) {
        this.classicalMusic = classicalMusic;
        this.rockMusic = rockMusic;
    }

	public void playMusic(MusicChoice choice) {
		Random random = new Random();
		
		int randomNumber = random.nextInt(3);
		
		if(choice == MusicChoice.CLASSICAL) {
			System.out.println("Playing " + classicalMusic.getSongs().get(randomNumber));
			return;
		}
		
		if(choice == MusicChoice.ROCK) {
			System.out.println("Playing " + rockMusic.getSongs().get(randomNumber));
			return;
		}
    }

}










