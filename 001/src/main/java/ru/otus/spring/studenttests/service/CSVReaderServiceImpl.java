package ru.otus.spring.studenttests.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CSVReaderServiceImpl implements CSVReaderService {

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
