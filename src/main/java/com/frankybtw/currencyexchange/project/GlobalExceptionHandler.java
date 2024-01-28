package com.frankybtw.currencyexchange.project;


import com.frankybtw.currencyexchange.project.exceptions.CurrencyConflictException;
import com.frankybtw.currencyexchange.project.exceptions.ExchangeRateConflictException;
import com.frankybtw.currencyexchange.project.exceptions.InvalidCurrencyDataException;
import com.frankybtw.currencyexchange.project.exceptions.PairNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CurrencyConflictException.class)
    public ResponseEntity<Map<String, Object>> handlerCurrencyConflictException(CurrencyConflictException ex) {
        return createJsonErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidCurrencyDataException.class)
    public ResponseEntity<Map<String, Object>> handlerInvalidCurrencyDataException(InvalidCurrencyDataException ex) {
        return createJsonErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(PairNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerPairNotFoundException(PairNotFoundException ex) {
        return createJsonErrorResponse(determineStatus(ex), ex.getMessage());
    }

    @ExceptionHandler(ExchangeRateConflictException.class)
    public ResponseEntity<Map<String, Object>> handlerExchangeRateConflictException(ExchangeRateConflictException ex) {
        return createJsonErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    private HttpStatus determineStatus(PairNotFoundException ex) {
        if (ex.getMessage().contains("Одна (или обе) валюта из валютной пары не существует в БД")) {
            return HttpStatus.NOT_FOUND;  // 404
        } else {
            return HttpStatus.CONFLICT;    // 409
        }
    }

    private ResponseEntity<Map<String, Object>> createJsonErrorResponse(HttpStatus status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }
}
