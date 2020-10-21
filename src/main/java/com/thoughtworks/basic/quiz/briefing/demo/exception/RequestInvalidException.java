package com.thoughtworks.basic.quiz.briefing.demo.exception;

class RequestInvalidException extends RuntimeException{
    public RequestInvalidException(String message) {
        super(message);
    }
}
