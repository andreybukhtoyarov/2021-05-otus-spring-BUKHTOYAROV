package ru.otus.spring.studenttests.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.studenttests.config.TestConfig;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestConfig.class})
class TestingServiceImplTest {

    @Autowired
    private TestingService testingService;

    @Test
    public void test() {
        testingService.startTest();
        ByteArrayOutputStream out = TestConfig.getOUTPUT_STREAM();
        String expected = "Enter your full name\n" +
                "Question: Question1\n" +
                "Answer options:\n" +
                "0. answer01 1. answer02 2. answer03 3. answer04\n" +
                "Question: Question2\n" +
                "Answer options:\n" +
                "0. answer05 1. answer06 2. answer07 3. answer08\n" +
                "Question: Question3\n" +
                "Answer options:\n" +
                "0. answer09 1. answer10 2. answer11 3. answer12\n" +
                "Question: Question4\n" +
                "Answer options:\n" +
                "0. answer13 1. answer14 2. answer15 3. answer16\n" +
                "Question: Question5\n" +
                "Answer options:\n" +
                "0. answer17 1. answer18 2. answer19 3. answer20\n" +
                "FullName passed the test successfully\n";
        assertEquals(expected, out.toString());
    }

}