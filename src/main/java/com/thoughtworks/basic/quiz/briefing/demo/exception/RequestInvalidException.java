package com.thoughtworks.basic.quiz.briefing.demo.exception;

// TODO GTB-4: - 无用的类应该及时清理
class RequestInvalidException extends RuntimeException{
    public RequestInvalidException(String message) {
        super(message);
    }
}
