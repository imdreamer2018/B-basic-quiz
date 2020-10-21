package com.thoughtworks.basic.quiz.briefing.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private long id;
    private String name;
    private long age;
    private String avatar;
    private String description;
}
