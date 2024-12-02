package com.project.habitual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(scanBasePackages = "com.project.habitual")
@RestController
public class HabitualApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabitualApplication.class, args);
	}
}
