package ru.otus.spring.studenttests.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderServiceTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        CSVReaderService csvReaderService = context.getBean(CSVReaderService.class);
        List<String[]> lines = csvReaderService.getCsvLines();
        String[] actual = lines.get(0);
        String[] expected = new String[]{"Question1", "answer01", "answer02", "answer03", "answer04", "2"};
        assertArrayEquals(expected, actual);
    }

}