package com.ever.br.api.control.gamer.domain.exception;

import org.springframework.http.HttpStatus;

public class DuplicatedObjectException extends RuntimeException{

    public DuplicatedObjectException(String msg) {
        super(msg);
    }
}
