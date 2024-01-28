package com.frankybtw.currencyexchange.project.repository.Impl;

import com.frankybtw.currencyexchange.project.exceptions.InvalidCurrencyDataException;
import com.frankybtw.currencyexchange.project.model.Currency;
import com.frankybtw.currencyexchange.project.repository.CurrencyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class CurrencyRepositoryImpl implements CurrencyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public boolean existsByCode(String code) {
        Long count = entityManager
                .createQuery("SELECT COUNT(c) FROM Currency c WHERE code = :code", Long.class)
                .setParameter("code", code)
                .getSingleResult();

        return count > 0;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return entityManager.createQuery("SELECT c FROM Currency c",
                Currency.class).getResultList();
    }

    @Override
    public Currency findByCode(String code) {
        try {
            return entityManager.createQuery("SELECT c FROM Currency c WHERE c.code = :code",
                            Currency.class).setParameter("code", code)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new InvalidCurrencyDataException("Валюта " + code + " не найдена.");
        }
    }

    @Override
    public void addCurrency(Currency currency) {
        entityManager.persist(currency);
    }

    @Override
    public void deleteCurrency(String code) {
        Currency currencyToDelete = entityManager.createQuery(
                        "SELECT c FROM Currency c WHERE c.code = :code", Currency.class)
                .setParameter("code", code)
                .getSingleResult();

        entityManager.remove(currencyToDelete);
    }
}
