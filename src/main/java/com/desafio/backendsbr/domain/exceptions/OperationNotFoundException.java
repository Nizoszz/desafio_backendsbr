package com.desafio.backendsbr.domain.exceptions;

public class OperationNotFoundException extends RuntimeException{
    public OperationNotFoundException(String message) {
        super(message);
    }
}
