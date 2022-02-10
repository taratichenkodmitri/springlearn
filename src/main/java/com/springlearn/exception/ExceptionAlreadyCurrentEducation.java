package com.springlearn.exception;

public class ExceptionAlreadyCurrentEducation extends Exception implements Exceptions {
    public ExceptionAlreadyCurrentEducation(Long schoolId) {
        super("Student already study in school with id: : " + schoolId);
    }
}
