package com.bloomberg.clustereddatawarehouse.exceptions;

import lombok.Data;

@Data
public class RequiredFieldException extends RuntimeException {
    private int status;
    private Object data;
    public RequiredFieldException(String message) {
        super(message);
    }

    public RequiredFieldException(int status, String message) {
        super(message);
        this.status = status;
    }

    public RequiredFieldException(int status, String message, Object data) {
        super(message);
        this.data = data;
        this.status = status;
    }
}
