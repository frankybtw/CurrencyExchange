package com.frankybtw.currencyexchange.project.repository;

import com.frankybtw.currencyexchange.project.model.Currency;
import com.frankybtw.currencyexchange.project.model.ExchangeRates;

import java.util.List;

public interface ExchangeRatesRepository {
    List<ExchangeRates> getAllExchangeRates();
    ExchangeRates getExchangeRate(Currency baseCurrency, Currency targetCurrency);
    void addExchangeRate(ExchangeRates exchangeRates);
    boolean existsByBaseAndTargetCurrency(Currency baseCurrency, Currency targetCurrency);
}
