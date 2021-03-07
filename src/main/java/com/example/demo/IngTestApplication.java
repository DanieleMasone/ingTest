package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * 
 * @author dmasone
 * @implSpec class used for start app
 *
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })  // Used for do curl
public class IngTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngTestApplication.class, args);
	}

}
