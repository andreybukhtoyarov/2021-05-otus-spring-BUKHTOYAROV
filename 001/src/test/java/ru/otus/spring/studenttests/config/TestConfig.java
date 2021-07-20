package ru.otus.spring.studenttests.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.spring.studenttests.mapper.AnswerMapper;
import ru.otus.spring.studenttests.mapper.QuestionMapper;
import ru.otus.spring.studenttests.service.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@Import(AppProperties.class)
@RequiredArgsConstructor
@SpringBootConfiguration
public class TestConfig {

    private final AppProperties appProperties;
    private final MessageSource messageSource;

    @Bean
    public String questionsFilePath() {
        if ("ru_RU".equals(appProperties.getLocalization())) {
            return "/questions_ru_RU.csv";
        }
        return "/questions.csv";
    }

    @Getter
    private final static ByteArrayOutputStream OUTPUT_STREAM = new ByteArrayOutputStream();
    @Getter
    private final static ByteArrayInputStream INPUT_STREAM = new ByteArrayInputStream(
            "FullName\n1\n2\n3\n0\n1".getBytes(StandardCharsets.UTF_8));

    @Bean
    public CSVReaderService getCSVReaderService() {
        return new CSVReaderServiceImpl(questionsFilePath());
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
        return new TestingServiceImpl(appProperties, getFillQuestionService(), messageSource, INPUT_STREAM, OUTPUT_STREAM);
    }

}
