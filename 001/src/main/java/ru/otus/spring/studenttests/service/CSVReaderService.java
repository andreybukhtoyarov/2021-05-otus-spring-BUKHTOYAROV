package ru.otus.spring.studenttests.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import ru.otus.spring.studenttests.model.Answer;
import ru.otus.spring.studenttests.model.Question;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CSVReaderService {

    private final String questionsFilePath;

    public List<String[]> getCsvLines() {
        List<String[]> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(
                Objects.requireNonNull(getClass().getResource(questionsFilePath)).getFile()))) {
            CSVReader csvReader = new CSVReader(reader);
            lines = csvReader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return lines;
    }

}
