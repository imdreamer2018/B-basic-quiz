package com.thoughtworks.basic.quiz.briefing.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Error {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
}
