package com.springlearn.exception;

public class ExceptionStudentNotFound extends Exception implements Exceptions {
    public ExceptionStudentNotFound(Long studnetId) {
        super("Student with id: " + studnetId + " is doesn't exist");
    }
}
