package ru.otus.spring.studenttests.service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.studenttests.mapper.AnswerMapper;
import ru.otus.spring.studenttests.mapper.QuestionMapper;
import ru.otus.spring.studenttests.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class FillQuestionService {

    private final CSVReaderService csvReaderService;

    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        List<String[]> lines = csvReaderService.getCsvLines();
        lines.forEach(line -> {
            Question question = questionMapper.map(line);
            questions.add(question);
            IntStream.range(1, 5).forEach(i -> question.getAnswers().add(answerMapper.map(line, i, question)));
        });
        return questions;
    }

}
