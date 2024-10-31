package com.firedb.Firegroup.exception;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
