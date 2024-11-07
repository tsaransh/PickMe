package com.pickme.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CarException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final  String message;

    public CarException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
