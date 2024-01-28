package com.frankybtw.currencyexchange.project.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PairNotFoundException extends RuntimeException {
    public PairNotFoundException(String message) {
        super(message);
    }
}