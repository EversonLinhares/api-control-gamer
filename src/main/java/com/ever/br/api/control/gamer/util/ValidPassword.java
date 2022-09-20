package com.ever.br.api.control.gamer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ValidPassword {
    @Autowired
    PasswordEncoder encoder;

    public Boolean validPassword(String passwordRequest, String passwordBanco) {
        Boolean valid = encoder.matches(passwordRequest,passwordBanco);
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return valid;
    }
}
