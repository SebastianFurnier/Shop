package com.project.shop.ExceptionHandler;

public class DataErrorException extends RuntimeException {
    public DataErrorException (String message) {
        super(message);
    }
}
