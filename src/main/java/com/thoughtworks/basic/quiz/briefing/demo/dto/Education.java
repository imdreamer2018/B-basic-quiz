package com.thoughtworks.basic.quiz.briefing.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thoughtworks.basic.quiz.briefing.demo.validation.CharSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "year can not empty")
    private long year;
    @NotEmpty(message = "title can not empty")
    @CharSize(min = 2, max = 256, message = "title char size must be between 1 and 256 bytes")
    private String title;
    @NotEmpty(message = "title can not empty")
    @CharSize(min = 1, max = 4096, message = "description char size must be between 1 and 256 bytes")
    private String description;

    //need refactor entity and response dto
    //so should create education dto with userId instead of User.
    //and I use JsonIgnoreProperties()
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"name", "age", "avatar", "description"})
    private User user;
}
