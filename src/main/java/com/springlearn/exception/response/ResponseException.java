package com.springlearn.exception.response;

public class ResponseException {
    private String message;

    public ResponseException() {
    }

    public ResponseException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
