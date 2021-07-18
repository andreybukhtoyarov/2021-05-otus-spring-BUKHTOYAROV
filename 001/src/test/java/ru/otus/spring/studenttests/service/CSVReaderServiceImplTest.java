package ru.otus.spring.studenttests.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.studenttests.config.TestConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderServiceImplTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        CSVReaderServiceImpl csvReaderService = context.getBean(CSVReaderServiceImpl.class);
        List<String[]> lines = csvReaderService.getCsvLines();
        String[] actual = lines.get(0);
        String[] expected = new String[]{"Question1", "answer01", "answer02", "answer03", "answer04", "2"};
        assertArrayEquals(expected, actual);
    }

}