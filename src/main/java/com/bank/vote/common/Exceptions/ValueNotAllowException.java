package com.bank.vote.common.Exceptions;

public class ValueNotAllowException extends RuntimeException {

    public ValueNotAllowException(String message) {
        super(message);
    }

    public ValueNotAllowException(String message, Throwable cause) {
        super(message, cause);
    }
}