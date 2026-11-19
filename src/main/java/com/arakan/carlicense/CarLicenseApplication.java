package com.arakan.carlicense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class CarLicenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarLicenseApplication.class, args);
	}

}
