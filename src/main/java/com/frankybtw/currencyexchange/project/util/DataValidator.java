package com.frankybtw.currencyexchange.project.util;

import com.frankybtw.currencyexchange.project.exceptions.InvalidCurrencyDataException;
import com.frankybtw.currencyexchange.project.model.Currency;
import com.frankybtw.currencyexchange.project.model.ExchangeRates;

public class DataValidator {
    public static void validateCurrencyFields(Currency currency) {
        if (currency.getCode().isBlank() ||
                (currency.getFullName() == null || currency.getFullName().isBlank()) ||
                (currency.getSign() == null || currency.getSign().isBlank())) {
            throw new InvalidCurrencyDataException("Отсутствует нужное поле формы.");
        }
    }

    public static void validateExchangeRateFields(ExchangeRates exchangeRates) {
        if (exchangeRates.getRate() == 0) {
            throw new InvalidCurrencyDataException("Отсутствует нужное поле формы.");
        }
    }
}
