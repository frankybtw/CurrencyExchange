package com.frankybtw.currencyexchange.project.service.Impl;



import com.frankybtw.currencyexchange.project.exceptions.ExchangeRateConflictException;
import com.frankybtw.currencyexchange.project.exceptions.InvalidCurrencyDataException;
import com.frankybtw.currencyexchange.project.exceptions.PairNotFoundException;
import com.frankybtw.currencyexchange.project.model.Currency;
import com.frankybtw.currencyexchange.project.model.ExchangeRates;
import com.frankybtw.currencyexchange.project.repository.CurrencyRepository;
import com.frankybtw.currencyexchange.project.repository.ExchangeRatesRepository;
import com.frankybtw.currencyexchange.project.repository.Impl.ExchangeRatesRepositoryImpl;
import com.frankybtw.currencyexchange.project.service.ExchangeRatesService;
import com.frankybtw.currencyexchange.project.util.DataValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {
    private final ExchangeRatesRepository repository;
    private final CurrencyRepository currencyRepository;

    public ExchangeRatesServiceImpl(ExchangeRatesRepositoryImpl repository, CurrencyRepository currencyRepository) {
        this.repository = repository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<ExchangeRates> getAllExchangeRates() {
        return repository.getAllExchangeRates();
    }

    @Override
    public ExchangeRates getExchangeRate(String wholeCode) {
        if (wholeCode.length() != 6) {
            throw new InvalidCurrencyDataException("Коды валют пары отсутствуют в адресе.");
        }

        String baseCode = wholeCode.substring(0, 3);
        String targetCode = wholeCode.substring(3, 6);

        Currency baseCurrency = currencyRepository.findByCode(baseCode);
        Currency targetCurrency = currencyRepository.findByCode(targetCode);

        if (!repository.existsByBaseAndTargetCurrency(baseCurrency, targetCurrency)) {
            throw new PairNotFoundException("Обменный курс для пары не найден.");
        }

        return repository.getExchangeRate(baseCurrency, targetCurrency);
    }

    @Override
    public void addExchangeRate(String baseCode, String targetCode, Double rate) {
        Currency baseCurrency = currencyRepository.findByCode(baseCode);
        Currency targetCurrency = currencyRepository.findByCode(targetCode);

        ExchangeRates exchangeRates = new ExchangeRates(null, baseCurrency, targetCurrency, rate);
        DataValidator.validateExchangeRateFields(exchangeRates);

        if (repository.existsByBaseAndTargetCurrency(baseCurrency, targetCurrency)) {
            throw new ExchangeRateConflictException("Валютная пара с таким кодом уже существует.");
        }

        if (!currencyRepository.existsByCode(baseCode) && !currencyRepository.existsByCode(targetCode)) {
            throw new PairNotFoundException("Одна (или обе) валюта из валютной пары не существует в БД.");
        }

        repository.addExchangeRate(exchangeRates);
    }
}
