package com.thoughtworks.basic.quiz.briefing.demo.dto;

import com.thoughtworks.basic.quiz.briefing.demo.validation.CharSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {

    private long id;
    @NotNull(message = "year can not empty")
    private long year;
    @NotEmpty(message = "title can not empty")
    @CharSize(min = 1, max = 256, message = "title char size must be between 1 and 256 bytes")
    private String title;
    @NotEmpty(message = "title can not empty")
    @CharSize(min = 1, max = 4096, message = "description char size must be between 1 and 256 bytes")
    private String description;
    private long userId;
}
