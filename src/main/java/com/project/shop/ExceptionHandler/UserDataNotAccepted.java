package com.project.shop.ExceptionHandler;

public class UserDataNotAccepted extends RuntimeException{
    public UserDataNotAccepted(String message) {
        super(message);
    }
}
