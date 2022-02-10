package com.springlearn.exception;

public class ExceptionNotValidQuestionably extends Exception implements Exceptions{
    public ExceptionNotValidQuestionably(String str) {
        super("Key " + str + " not found in QuestionCode");
    }
}
