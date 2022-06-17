package com.ever.br.apicontas.service.exception;

public class DuplicatedObjectException extends RuntimeException{

    public DuplicatedObjectException(String msg) {
        super(msg);
    }
}
