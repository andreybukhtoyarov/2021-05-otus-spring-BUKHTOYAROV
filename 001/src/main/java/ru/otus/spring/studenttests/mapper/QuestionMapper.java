package ru.otus.spring.studenttests.mapper;

import org.springframework.stereotype.Component;
import ru.otus.spring.studenttests.model.Answer;
import ru.otus.spring.studenttests.model.Question;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {

    public Question map(String[] line) {
        Question question = new Question();
        question.setQuestion(line[0]);
        question.setAnswers(new ArrayList<>());
        return question;
    }

}
