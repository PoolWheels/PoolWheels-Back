package com.UNIWheels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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
