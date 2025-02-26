package com.locadora.filmes.exceptions;

public class CpfCnpjInvalidException extends RuntimeException {
    public CpfCnpjInvalidException(String message) {
        super(message);
    }
}
