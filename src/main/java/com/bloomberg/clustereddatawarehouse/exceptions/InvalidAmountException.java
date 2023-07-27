package com.bloomberg.clustereddatawarehouse.exceptions;

import lombok.Data;

@Data
public class InvalidAmountException extends RuntimeException {
    private int status;
    private Object data;

    public InvalidAmountException(String message) {
        super(message);
    }

    public InvalidAmountException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public InvalidAmountException(int status, String message, Object data) {
        super(message);
        this.data = data;
        this.status = status;
    }
}
