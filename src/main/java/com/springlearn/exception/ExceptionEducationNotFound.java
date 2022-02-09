package com.springlearn.exception;

public class ExceptionEducationNotFound extends Exception implements Exceptions{
    public ExceptionEducationNotFound(Long educationId) {

        super("Education with id: " + educationId + " is doesn't exist");
    }
}
