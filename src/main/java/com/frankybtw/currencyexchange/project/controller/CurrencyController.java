package com.frankybtw.currencyexchange.project.controller;


import com.frankybtw.currencyexchange.project.model.Currency;
import com.frankybtw.currencyexchange.project.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class CurrencyController {
    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/currencies")
    public List<Currency> getAllCurrencies() {
        return service.getAllCurrencies();
    }

    @GetMapping("/currency/{code}")
    public Currency findByCode(@PathVariable String code) {
        return service.findByCode(code);
    }

    @PostMapping("/currencies")
    public void addCurrency(@RequestBody Currency currency) {
        service.addCurrency(currency);
    }

    @DeleteMapping("/currency_delete/{code}")
    public void deleteCurrency(@PathVariable String code) {
        service.deleteCurrency(code);
    }
}
