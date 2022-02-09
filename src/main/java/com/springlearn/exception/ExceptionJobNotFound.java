package com.springlearn.exception;

public class ExceptionJobNotFound extends Exception implements Exceptions{
    public ExceptionJobNotFound(Long jobId) {

        super("Job with id: " + jobId + " is doesn't exist");
    }
}
