package com.thoughtworks.basic.quiz.briefing.demo.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
