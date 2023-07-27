package com.bloomberg.clustereddatawarehouse.exceptions;

public class BlankStringException extends RuntimeException {
    private int status;

    public BlankStringException(String message) {
        super(message);
    }

    public BlankStringException(int status, String message) {
        super(message);
        this.status = status;
    }
}
