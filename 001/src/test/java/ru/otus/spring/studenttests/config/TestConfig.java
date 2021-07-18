package ru.otus.spring.studenttests.config;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.studenttests.mapper.AnswerMapper;
import ru.otus.spring.studenttests.mapper.QuestionMapper;
import ru.otus.spring.studenttests.service.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@Configuration
public class TestConfig {

    private final String path = "/questions.csv";

    @Bean
    public Set<Integer> answerIn() {
        return Set.of(0, 1, 2, 3);
    }

    @Getter
    private final static ByteArrayOutputStream OUTPUT_STREAM = new ByteArrayOutputStream();
    @Getter
    private final static ByteArrayInputStream INPUT_STREAM = new ByteArrayInputStream(
            "FullName\n1\n2\n3\n0\n1".getBytes(StandardCharsets.UTF_8));

    @Bean
    public CSVReaderService getCSVReaderService() {
        return new CSVReaderServiceImpl(path);
    }

    @Bean
    public AnswerMapper getAnswerMapper() {
        return new AnswerMapper();
    }

    @Bean
    public QuestionMapper getQuestionMapper() {
        return new QuestionMapper();
    }

    @Bean
    public FillQuestionService getFillQuestionService() {
        return new FillQuestionServiceImpl(getCSVReaderService(), getAnswerMapper(), getQuestionMapper());
    }

    @Bean
    public TestingService getTestingServiceImpl() {
        return new TestingServiceImpl(getFillQuestionService(), INPUT_STREAM, OUTPUT_STREAM, answerIn());
    }

}
