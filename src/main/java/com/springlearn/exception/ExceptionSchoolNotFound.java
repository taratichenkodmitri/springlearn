package com.springlearn.exception;

public class ExceptionSchoolNotFound extends Exception implements Exceptions {
    public ExceptionSchoolNotFound(Long schoolId) {
        super("School with id: " + schoolId + " is doesn't exist");
    }
}
