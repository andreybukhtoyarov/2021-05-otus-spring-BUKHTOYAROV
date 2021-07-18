package ru.otus.spring.studenttests.mapper;

import org.springframework.stereotype.Component;
import ru.otus.spring.studenttests.model.Answer;
import ru.otus.spring.studenttests.model.Question;

@Component
public class AnswerMapper {

    public Answer map(String[] line, int i, Question question) {
        Answer answer = new Answer();
        answer.setAnswer(line[i]);
        answer.setCorrect(i == Integer.parseInt(line[5]));
        answer.setQuestion(question);
        return answer;
    }

}
