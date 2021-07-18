package ru.otus.spring.studenttests.service;

import java.util.List;

public interface CSVReaderService {

    List<String[]> getCsvLines();
    
}
