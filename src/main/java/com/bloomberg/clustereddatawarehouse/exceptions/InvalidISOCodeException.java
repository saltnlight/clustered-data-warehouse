package com.bloomberg.clustereddatawarehouse.exceptions;

import lombok.Data;

@Data
public class InvalidISOCodeException extends RuntimeException {
    private int status;
    private Object data;

    public InvalidISOCodeException(String message) {
        super(message);
    }

    public InvalidISOCodeException(int status, String message) {
        super(message);
        this.status = status;
    }

    public InvalidISOCodeException(int status, String message, Object data) {
        super(message);
        this.data = data;
        this.status = status;
    }
}
