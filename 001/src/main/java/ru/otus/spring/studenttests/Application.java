package ru.otus.spring.studenttests;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.studenttests.service.PrintService;

public class Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        PrintService printService = context.getBean(PrintService.class);
        printService.print();
    }

}
