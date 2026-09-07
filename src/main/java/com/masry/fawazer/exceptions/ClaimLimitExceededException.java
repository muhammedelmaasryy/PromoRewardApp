package com.masry.fawazer.exceptions;

public class ClaimLimitExceededException extends RuntimeException {
    public ClaimLimitExceededException(String message) {
        super(message);
    }
}
