package com.frankybtw.currencyexchange.project.repository.Impl;


import com.frankybtw.currencyexchange.project.model.Currency;
import com.frankybtw.currencyexchange.project.model.ExchangeRates;
import com.frankybtw.currencyexchange.project.repository.CurrencyRepository;
import com.frankybtw.currencyexchange.project.repository.ExchangeRatesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ExchangeRatesRepositoryImpl implements ExchangeRatesRepository {
    @PersistenceContext
    public EntityManager entityManager;
    public CurrencyRepository currencyRepository;

    public ExchangeRatesRepositoryImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public boolean existsByBaseAndTargetCurrency(Currency baseCurrency, Currency targetCurrency) {
        Long count = entityManager.createQuery("SELECT COUNT(e) FROM ExchangeRates e " +
                "WHERE e.baseCurrency = :baseCurrency AND e.targetCurrency = :targetCurrency", Long.class)
                .setParameter("baseCurrency", baseCurrency)
                .setParameter("targetCurrency", targetCurrency)
                .getSingleResult();

        return count > 0;
    }

    @Override
    public List<ExchangeRates> getAllExchangeRates() {
        return entityManager.createQuery("SELECT e FROM ExchangeRates e",
                        ExchangeRates.class).getResultList();
    }

    @Override
    public ExchangeRates getExchangeRate(Currency baseCurrency, Currency targetCurrency) {
        return entityManager
                .createQuery("SELECT eR FROM ExchangeRates eR " +
                                "WHERE eR.baseCurrency = :baseCurrency " +
                                "AND eR.targetCurrency = :targetCurrency", ExchangeRates.class)
                .setParameter("baseCurrency", baseCurrency)
                .setParameter("targetCurrency", targetCurrency)
                .getSingleResult();
    }

    @Override
    public void addExchangeRate(ExchangeRates exchangeRates) {
        entityManager.persist(exchangeRates);
    }

}
