package ru.otus.spring.studenttests.service;

import ru.otus.spring.studenttests.model.Question;

import java.util.List;

public interface FillQuestionService {

    List<Question> getQuestions();

}
