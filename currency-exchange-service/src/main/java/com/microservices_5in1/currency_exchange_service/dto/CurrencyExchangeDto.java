package com.microservices_5in1.currency_exchange_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
public class CurrencyExchangeDto {

    public Long id;
    public String from;
    public String to;
    public BigDecimal conversionMultiple;
    public String environment;

    public CurrencyExchangeDto(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }
}
