package com.frankybtw.currencyexchange.project.service;

import com.frankybtw.currencyexchange.project.model.ExchangeRates;

import java.util.List;

public interface ExchangeRatesService {
    List<ExchangeRates> getAllExchangeRates();
    ExchangeRates getExchangeRate(String wholeCode);
    void addExchangeRate(String baseCode, String targetCode, Double rate);
}
