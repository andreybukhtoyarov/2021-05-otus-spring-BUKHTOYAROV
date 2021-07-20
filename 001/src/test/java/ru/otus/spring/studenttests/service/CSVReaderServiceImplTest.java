package ru.otus.spring.studenttests.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.studenttests.config.TestConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestConfig.class})
class CSVReaderServiceImplTest {

    @Autowired
    private CSVReaderServiceImpl csvReaderService;

    @Test
    public void test() {
        List<String[]> lines = csvReaderService.getCsvLines();
        String[] actual = lines.get(0);
        String[] expected = new String[]{"Question1", "answer01", "answer02", "answer03", "answer04", "2"};
        assertArrayEquals(expected, actual);
    }

}