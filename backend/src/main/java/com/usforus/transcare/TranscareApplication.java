package com.usforus.transcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TranscareApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranscareApplication.class, args);
	}

}
