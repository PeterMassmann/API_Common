package com.easyspec.errors;

public class ErrorLogInstantiationException extends RuntimeException {

    private final Exception exception;

    public ErrorLogInstantiationException(Exception exception) {
        super();
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

}
