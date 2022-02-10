package com.springlearn.exception;

public class ExceptionTeacherNotFound extends Exception implements Exceptions {
    public ExceptionTeacherNotFound(Long teacherId) {
        super("Teacher with id: " + teacherId + " is doesn't exist");
    }
}

