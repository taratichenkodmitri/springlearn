package com.springlearn.exception;

public class ExceptionAlreadyCurrentEducation extends Exception implements Exceptions {
    public ExceptionAlreadyCurrentEducation(Long schoolId) {
        super("School with id: " + schoolId + " is doesn't exist");
    }
}
