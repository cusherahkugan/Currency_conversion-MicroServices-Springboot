package com.demo.currency_conversion_service;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    private final RestClient restClient;

    public CurrencyConversionController() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8000")
                .build();
    }

    // Matches the JSON returned by the exchange service
    record ExchangeResponse(Long id, String from, String to,
                            BigDecimal conversionMultiple, String environment) {}

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convert(@PathVariable String from,
                                      @PathVariable String to,
                                      @PathVariable BigDecimal quantity) {

        ExchangeResponse rate = restClient.get()
                .uri("/currency-exchange/from/{from}/to/{to}", from, to)
                .retrieve()
                .body(ExchangeResponse.class);

        return new CurrencyConversion(
                rate.id(), from, to,
                rate.conversionMultiple(),
                quantity,
                quantity.multiply(rate.conversionMultiple()),
                rate.environment());
    }
}