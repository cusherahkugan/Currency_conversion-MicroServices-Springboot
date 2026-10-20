package com.demo.currency_conversion_service;

import java.math.BigDecimal;

public record CurrencyConversion(
        Long id,
        String from,
        String to,
        BigDecimal conversionMultiple,
        BigDecimal quantity,
        BigDecimal totalCalculatedAmount,
        String environment) {
}