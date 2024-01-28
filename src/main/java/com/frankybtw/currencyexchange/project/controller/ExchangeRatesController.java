package com.frankybtw.currencyexchange.project.controller;

import com.frankybtw.currencyexchange.project.model.ExchangeRates;
import com.frankybtw.currencyexchange.project.service.ExchangeRatesService;
import com.frankybtw.currencyexchange.project.service.Impl.ExchangeRatesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExchangeRatesController {
    private final ExchangeRatesService service;

    public ExchangeRatesController(ExchangeRatesServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/exchangeRates")
    public List<ExchangeRates> getAllExchangeRates() {
        return service.getAllExchangeRates();
    }

    @GetMapping("/exchangeRate/{wholeCode}")
    public ExchangeRates getExchangeRate(@PathVariable String wholeCode) {
        return service.getExchangeRate(wholeCode.toUpperCase());
    }

    @PostMapping("/exchangeRates")
    public void addExchangeRate(@RequestBody String baseCode, String targetCode, Double rate) {
        System.out.println("baseCode:" + baseCode + "\n targetCode:" +  targetCode + "\nrate:" + rate);
        service.addExchangeRate(baseCode, targetCode, rate);
    }
}
