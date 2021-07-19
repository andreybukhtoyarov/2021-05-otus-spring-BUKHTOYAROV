package ru.otus.spring.studenttests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

@Configuration
public class Config {

    @Bean
    public String questionsFilePath() {
        return "/questions.csv";
    }

    @Bean
    public Set<Integer> answerIn() {
        return Set.of(0, 1, 2, 3);
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
