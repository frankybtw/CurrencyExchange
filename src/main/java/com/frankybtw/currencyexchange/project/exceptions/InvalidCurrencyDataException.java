package com.frankybtw.currencyexchange.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCurrencyDataException extends RuntimeException {
    public InvalidCurrencyDataException(String message) {
        super(message);
    }
}
