package com.springlearn.exception;

public class EducationExceptionStudentNotFound extends Exception implements EducationException{
    public EducationExceptionStudentNotFound(String msg) {
        super(msg);
    }
}
