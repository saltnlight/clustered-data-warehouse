package com.bloomberg.clustereddatawarehouse.exceptions;

public class BlankStringException extends RuntimeException {
    public BlankStringException(String message) {
        super(message);
    }
}
