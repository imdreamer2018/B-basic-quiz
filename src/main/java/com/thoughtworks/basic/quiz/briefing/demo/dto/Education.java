package com.thoughtworks.basic.quiz.briefing.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    private long id;
    private long year;
    private String title;
    private String description;
    private long userId;
}
