package com.thoughtworks.basic.quiz.briefing.demo.dto;

import com.thoughtworks.basic.quiz.briefing.demo.validation.CharSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private long id;
    @NotEmpty(message = "name can not empty")
    @CharSize(min = 1, max = 128, message = "name char size invalid, must between 1 and 128 bytes!")
    private String name;
    @NotNull(message = "age can not empty")
    @Min(value = 16, message = "age valued invalid, must greater than 16!")
    private long age;
    @NotEmpty(message = "avatar can not empy")
    @CharSize(min = 8, max = 512, message = "avatar char size invalid, must between 8 and 512 bytes!")
    private String avatar;
    @CharSize(max = 1024, message = "description char size invalid, must between 0 and 1024 bytes!")
    private String description;
}
