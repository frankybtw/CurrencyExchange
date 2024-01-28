package com.frankybtw.currencyexchange.project.service.Impl;


import com.frankybtw.currencyexchange.project.exceptions.CurrencyConflictException;
import com.frankybtw.currencyexchange.project.model.Currency;
import com.frankybtw.currencyexchange.project.repository.CurrencyRepository;
import com.frankybtw.currencyexchange.project.service.CurrencyService;
import com.frankybtw.currencyexchange.project.util.DataValidator;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository repository;

    public CurrencyServiceImpl(CurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return repository.getAllCurrencies();
    }

    @Override
    public Currency findByCode(String code) {
        return repository.findByCode(code.toUpperCase());
    }

    @Override
    public void addCurrency(Currency currency) {
        DataValidator.validateCurrencyFields(currency);

        if (repository.existsByCode(currency.getCode())) {
            throw new CurrencyConflictException(
                    "Валюта с таким кодом - " + currency.getCode() + " уже существует.");
        }

        repository.addCurrency(currency);
    }

    @Override
    public void deleteCurrency(String code) {
        repository.deleteCurrency(code.toUpperCase());
    }
}
