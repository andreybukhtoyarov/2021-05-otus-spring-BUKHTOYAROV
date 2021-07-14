package ru.otus.spring.studenttests.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
public class Answer {

    private BigDecimal id;
    private String answer;
    private Boolean correct;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Question question;

}
