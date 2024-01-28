package com.frankybtw.currencyexchange.project.model;


import jakarta.persistence.*;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "baseCurrencyId", referencedColumnName = "id")
    private Currency baseCurrency;
    @ManyToOne
    @JoinColumn(name = "targetCurrencyId", referencedColumnName = "id")
    private Currency targetCurrency;
    private Double rate;

    public ExchangeRates() {};

    public ExchangeRates(Long id, Currency baseCurrency, Currency targetCurrency, Double rate) {
        this.id = id;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "id=" + id +
                ", baseCurrencyId=" + baseCurrency +
                ", targetCurrencyId=" + targetCurrency +
                ", rate=" + rate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public Double getRate() {
        return rate;
    }
}
