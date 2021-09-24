package com.bff.integration.schema.exceptions;

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }
}
