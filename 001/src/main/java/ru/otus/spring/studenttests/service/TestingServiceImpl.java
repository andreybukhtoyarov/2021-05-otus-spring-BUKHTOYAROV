package ru.otus.spring.studenttests.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.studenttests.config.AppProperties;
import ru.otus.spring.studenttests.model.Answer;
import ru.otus.spring.studenttests.model.Question;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {

    private final AppProperties appProperties;
    private Locale locale;

    private final FillQuestionService fillQuestionService;
    private final MessageSource messageSource;

    private final InputStream inputStream;
    private final OutputStream outputStream;

    private String fullName;
    private final static Set<Integer> ANSWER_IN = Set.of(0, 1, 2, 3);
    private final Map<Question, Integer> answers = new HashMap<>();

    @PostConstruct
    private void initLocale() {
        locale = Locale.forLanguageTag(appProperties.getLocalization());
    }

    @Override
    public void startTest() {
        List<Question> questions = fillQuestionService.getQuestions();
        try (PrintWriter writer = new PrintWriter(outputStream);
             Scanner scanner = new Scanner(inputStream)) {
            writer.println(messageSource.getMessage("strings.greetings", null, locale));
            writer.flush();
            fullName = scanner.nextLine();
            questions.forEach(question -> {
                String[] answersArray = new String[] {
                        question.getAnswers().get(0).getAnswer(),
                        question.getAnswers().get(1).getAnswer(),
                        question.getAnswers().get(2).getAnswer(),
                        question.getAnswers().get(3).getAnswer()};
                writer.println(messageSource.getMessage("strings.question", null, locale) + " " + question.getQuestion());
                writer.println(messageSource.getMessage("strings.answeropt", null, locale));
                writer.println(String.format(
                        "0. %s 1. %s 2. %s 3. %s",
                        messageSource.getMessage("strings.answer00", answersArray, locale),
                        messageSource.getMessage("strings.answer01", answersArray, locale),
                        messageSource.getMessage("strings.answer02", answersArray, locale),
                        messageSource.getMessage("strings.answer03", answersArray, locale)
                        )
                );
                writer.flush();
                int answer = -1;
                while (answer == -1) {
                    try {
                        answer = scanner.nextInt();
                        if (!ANSWER_IN.contains(answer)) {
                            answer = -1;
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        writer.println(messageSource.getMessage("strings.error", null, locale));
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
            writer.println(fullName + " " + messageSource.getMessage("strings.success", null, locale));
        } else {
            writer.println(fullName + " " + messageSource.getMessage("strings.fail", null, locale));
        }
    }

}
