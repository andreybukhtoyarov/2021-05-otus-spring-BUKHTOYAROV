package ru.otus.spring.studenttests.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.studenttests.model.Answer;
import ru.otus.spring.studenttests.model.Question;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {

    private final FillQuestionService fillQuestionService;

    private final InputStream inputStream;
    private final OutputStream outputStream;

    private String fullName;
    private final Set<Integer> answerIn;
    private final Map<Question, Integer> answers = new HashMap<>();

    @Override
    public void startTest() {
        List<Question> questions = fillQuestionService.getQuestions();
        try (PrintWriter writer = new PrintWriter(outputStream);
             Scanner scanner = new Scanner(inputStream)) {
            writer.println("Enter your full name");
            writer.flush();
            fullName = scanner.nextLine();
            questions.forEach(question -> {
                writer.println("Question: " + question.getQuestion());
                writer.println("Answer options:");
                writer.println(String.format(
                                "0. %s 1. %s 2. %s 3. %s",
                                question.getAnswers().get(0).getAnswer(),
                                question.getAnswers().get(1).getAnswer(),
                                question.getAnswers().get(2).getAnswer(),
                                question.getAnswers().get(3).getAnswer()
                        )
                );
                writer.flush();
                int answer = -1;
                while (answer == -1) {
                    try {
                        answer = scanner.nextInt();
                        if (!answerIn.contains(answer)) {
                            answer = -1;
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        writer.println("Please enter number in 0, 1, 2, 3.");
                        writer.flush();
                    }
                }
                answers.put(question, answer);
            });
            printTestResult(writer);
        }
    }

    private void printTestResult(PrintWriter writer) {
        boolean test = true;
        for (Question question : answers.keySet()) {
            int intAnswer = answers.get(question);
            Answer answer = question.getAnswers().get(intAnswer);
            if (!answer.getCorrect()) {
                test = false;
                break;
            }
        }
        if (test) {
            writer.println(fullName + " passed the test successfully");
        } else {
            writer.println(fullName + " failed the test");
        }
    }

}
