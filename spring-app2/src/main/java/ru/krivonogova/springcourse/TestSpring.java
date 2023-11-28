package ru.krivonogova.springcourse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        MusicPlayer musicPlayer = context.getBean(MusicPlayer.class);
        
        System.out.println(musicPlayer.playMusic());
        
        context.close();
    }
}











