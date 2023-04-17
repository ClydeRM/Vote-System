package com.bank.vote.common.Exceptions;
public class VoteItemAlreadyExistedException extends RuntimeException {
    public VoteItemAlreadyExistedException(String message) {
        super(message);
    }

    public VoteItemAlreadyExistedException(String message, Throwable cause) {
        super(message, cause);
    }
}