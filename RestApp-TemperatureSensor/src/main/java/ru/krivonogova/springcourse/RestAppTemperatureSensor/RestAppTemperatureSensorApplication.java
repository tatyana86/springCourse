package ru.krivonogova.springcourse.RestAppTemperatureSensor;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestAppTemperatureSensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestAppTemperatureSensorApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
