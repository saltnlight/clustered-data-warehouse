package com.bloomberg.clustereddatawarehouse.exceptions;

import lombok.Data;

@Data
public class FutureDateException extends RuntimeException {
    private int status;
    private Object data;

    public FutureDateException(String message) {
        super(message);
    }

    public FutureDateException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public FutureDateException(int status, String message, Object data) {
        super(message);
        this.data = data;
        this.status = status;
    }
}
