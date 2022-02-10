package com.springlearn.exception;

public class ExceptionCurrentEducationNotFound extends Exception implements Exceptions{
    public ExceptionCurrentEducationNotFound() {
        super("Student nowhere to study");
    }
}
