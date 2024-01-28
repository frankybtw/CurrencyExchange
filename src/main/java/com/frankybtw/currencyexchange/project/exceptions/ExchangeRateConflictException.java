package com.frankybtw.currencyexchange.project.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExchangeRateConflictException extends RuntimeException {
    public ExchangeRateConflictException(String message) {
        super(message);
    }
}
