package com.frankybtw.currencyexchange.project.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CurrencyConflictException extends RuntimeException {
    public CurrencyConflictException(String message) {
        super(message);
    }
}
