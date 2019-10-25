package com.cognizant.intr.exceptions;

public class CustomerStatementProcessorException extends RuntimeException{
    private String action;
    private String errorMessage;
    public CustomerStatementProcessorException(String action, String message) {
        this.action = action;
        this.errorMessage = message;
    }
}
