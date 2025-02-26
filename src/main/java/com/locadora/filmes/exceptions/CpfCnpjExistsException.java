package com.locadora.filmes.exceptions;

public class CpfCnpjExistsException extends RuntimeException {
    public CpfCnpjExistsException(String message) {
        super(message);
    }
}
