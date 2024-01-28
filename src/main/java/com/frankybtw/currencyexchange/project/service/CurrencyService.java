package com.frankybtw.currencyexchange.project.service;

import com.frankybtw.currencyexchange.project.model.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getAllCurrencies();
    Currency findByCode(String code);
    void addCurrency(Currency currency);
    void deleteCurrency(String code);
}
