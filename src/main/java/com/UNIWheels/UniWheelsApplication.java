package com.UNIWheels;

import com.UNIWheels.repository.UserDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class UniWheelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniWheelsApplication.class, args);
	}

}
