package com.springlearn.exception;

public class EducationExceptionSchoolNotFound extends Exception implements EducationException{
    public EducationExceptionSchoolNotFound(String msg) {
        super(msg);
    }
}
