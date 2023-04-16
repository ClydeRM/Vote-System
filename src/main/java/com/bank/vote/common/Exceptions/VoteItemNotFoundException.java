package com.bank.vote.common.Exceptions;
public class VoteItemNotFoundException extends RuntimeException {

    public VoteItemNotFoundException(String message) {
        super(message);
    }

    public VoteItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}