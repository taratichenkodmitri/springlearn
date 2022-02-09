package com.springlearn.exception;

public class ExceptionTeacherNotFound extends Exception implements Exceptions {
    public ExceptionTeacherNotFound(Long teacherId) {
        super("School with id: " + teacherId + " is doesn't exist");
    }
}

