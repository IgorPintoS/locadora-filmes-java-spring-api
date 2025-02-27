package com.locadora.filmes.exceptions;

public class FilmeExistsException extends RuntimeException {
    public FilmeExistsException(String message) {
        super(message);
    }
}
