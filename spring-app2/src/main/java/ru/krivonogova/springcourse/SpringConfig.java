package ru.krivonogova.springcourse;

import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public ClassicalMusic classicalMusic() {
        return new ClassicalMusic();
    }

    @Bean
    public RockMusic rockMusic() {
        return new RockMusic();
    }
    
    @Bean
    public JazzMusic jazzMusic() {
        return new JazzMusic();
    }
    
    @Bean
    public List<Music> musicList() {
        return Arrays.asList(classicalMusic(), rockMusic(), jazzMusic());
    }

    @Bean
    public MusicPlayer musicPlayer() {
        return new MusicPlayer(musicList());
    }
    
    @Bean
    public Computer computer() {
        return new Computer(musicPlayer());
    }

}
