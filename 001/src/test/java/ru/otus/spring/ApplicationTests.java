package ru.otus.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.studenttests.config.TestConfig;

@SpringBootTest(classes = {TestConfig.class})
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
