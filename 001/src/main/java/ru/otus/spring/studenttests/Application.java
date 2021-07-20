package ru.otus.spring.studenttests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.studenttests.service.TestingServiceImpl;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args)
				.getBean(TestingServiceImpl.class).startTest();
	}

}
