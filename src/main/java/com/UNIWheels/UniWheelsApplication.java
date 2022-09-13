package com.UNIWheels;

// It's importing the Spring Boot application class and the annotation that marks a class as a Spring Boot application.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * It's a Spring Boot application
 */
@SpringBootApplication
public class UniWheelsApplication {

	/**
	 * The main function of the application. It is the entry point of the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(UniWheelsApplication.class, args);
	}

}
