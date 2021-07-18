package ru.otus.spring.studenttests;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.studenttests.config.Config;
import ru.otus.spring.studenttests.service.TestingService;
import ru.otus.spring.studenttests.service.TestingServiceImpl;

@ComponentScan
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        TestingService testingService = context.getBean(TestingServiceImpl.class);
        testingService.startTest();
    }

}
