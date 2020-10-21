package com.thoughtworks.basic.quiz.briefing.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestInvalidException.class)
    public ResponseEntity<Error> handle(RequestInvalidException e) {
        Error errorResult = Error.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handle(ResourceNotFoundException e) {
        Error errorResult = Error.builder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handle(MethodArgumentNotValidException e) {
        Error errorResult = Error.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> handle(ConstraintViolationException e) {
        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<?> constraint : e.getConstraintViolations()) {
            messageList.add(constraint.getMessage());
        }
        Error errorResult = Error.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(messageList.toString())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }


}
