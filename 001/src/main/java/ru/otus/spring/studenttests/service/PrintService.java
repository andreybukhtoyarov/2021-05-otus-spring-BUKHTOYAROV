package ru.otus.spring.studenttests.service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.studenttests.model.Question;

import java.util.List;

@RequiredArgsConstructor
public class PrintService {

    private final FillQuestionService fillQuestionService;

    public void print() {
        List<Question> questions = fillQuestionService.getQuestions();
        for (Question question : questions) {
            System.out.printf("Question: %s?%n", question.getQuestion());
            System.out.printf("Possible answers: %s, %s, %s, %s%n",
                    question.getAnswers().get(0).getAnswer(),
                    question.getAnswers().get(1).getAnswer(),
                    question.getAnswers().get(2).getAnswer(),
                    question.getAnswers().get(3).getAnswer());
        }
    }

}
