package com.frankybtw.currencyexchange.project.repository;

import com.frankybtw.currencyexchange.project.model.Currency;

import java.util.List;

public interface CurrencyRepository {
    List<Currency> getAllCurrencies();
    Currency
    findByCode(String code);
    void addCurrency(Currency currency);
    void deleteCurrency(String code);
    boolean existsByCode(String code);
}
