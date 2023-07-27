package com.bloomberg.clustereddatawarehouse.exceptions;

public class SameISOCurrencyException extends RuntimeException {
    private int status;
    private Object data;
    public SameISOCurrencyException(String message) {
        super(message);
    }

    public SameISOCurrencyException(int status, String message) {
        super(message);
        this.status = status;
    }

    public SameISOCurrencyException(int status, String message, Object data) {
        super(message);
        this.data = data;
        this.status = status;
    }
}
