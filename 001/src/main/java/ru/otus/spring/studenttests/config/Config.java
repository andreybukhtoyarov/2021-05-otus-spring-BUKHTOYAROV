package ru.otus.spring.studenttests.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.io.OutputStream;

@RequiredArgsConstructor
@SpringBootConfiguration
public class Config {

    private final AppProperties appProperties;

    @Bean
    public String questionsFilePath() {
        if ("ru-RU".equals(appProperties.getLocalization())) {
            return "/questions_ru_RU.csv";
        }
        return "/questions.csv";
    }

    @Bean
    public InputStream getInputStream() {
        return System.in;
    }

    @Bean
    public OutputStream getOutputStream() {
        return System.out;
    }

}
