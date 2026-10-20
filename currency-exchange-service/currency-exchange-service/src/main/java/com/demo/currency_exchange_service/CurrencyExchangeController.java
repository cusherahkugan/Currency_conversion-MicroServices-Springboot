package com.demo.currency_exchange_service;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurrencyExchangeController {

    private final CurrencyExchangeRepository repository;
    private final Environment environment;

    public CurrencyExchangeController(CurrencyExchangeRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange exchange = repository.findByFromAndTo(from, to);
        if (exchange == null) {
            throw new RuntimeException("Unable to find rate for " + from + " to " + to);
        }
        exchange.setEnvironment(environment.getProperty("local.server.port"));
        return exchange;
    }
}