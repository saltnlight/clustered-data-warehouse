package com.bloomberg.clustereddatawarehouse.exceptions;

import lombok.Data;

@Data
public class UniqueIdExistsException extends RuntimeException {
    private int status;
    private Object data;
    public UniqueIdExistsException(String message) {
        super(message);
    }

    public UniqueIdExistsException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public UniqueIdExistsException(int status, String message, Object data) {
        super(message);
        this.data = data;
        this.status = status;
    }
}
