package com.locadora.filmes.exceptions;

public class ReservaFilmeNotFoundException extends RuntimeException {
    public ReservaFilmeNotFoundException(String message) {
        super(message);
    }
}
