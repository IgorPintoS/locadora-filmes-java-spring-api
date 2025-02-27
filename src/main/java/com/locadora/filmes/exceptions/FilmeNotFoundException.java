package com.locadora.filmes.exceptions;

public class FilmeNotFoundException extends RuntimeException {
    public FilmeNotFoundException(String message) {
        super(message);
    }
}
