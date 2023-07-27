package com.bloomberg.clustereddatawarehouse.exceptions;

import lombok.Data;

@Data
public class InvalidAmountException extends RuntimeException {
    private int status;
    private Object data;

    public InvalidAmountException(String message) {
        super(message);
    }

    public InvalidAmountException(int status, String message) {
        super(message);
        this.status = status;
    }

    public InvalidAmountException(int status, String message, Object data) {
        super(message);
        this.data = data;
        this.status = status;
    }
}
