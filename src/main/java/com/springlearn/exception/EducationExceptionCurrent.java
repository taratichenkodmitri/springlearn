package com.springlearn.exception;

public class EducationExceptionCurrent extends Exception implements EducationException{
    public EducationExceptionCurrent(String msg) {
        super(msg);
    }
}
