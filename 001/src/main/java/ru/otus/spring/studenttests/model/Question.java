package ru.otus.spring.studenttests.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Question {

    private BigDecimal id;
    private String question;
    //@ToString.Exclude
    //@EqualsAndHashCode.Exclude
    private List<Answer> answers;

}
