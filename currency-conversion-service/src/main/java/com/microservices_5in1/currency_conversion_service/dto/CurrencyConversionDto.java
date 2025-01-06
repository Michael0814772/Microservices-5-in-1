package com.microservices_5in1.currency_conversion_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyConversionDto {

    public Long id;
    public String from;
    public String to;
    public BigDecimal quantity;
    public BigDecimal conversionMultiple;
    public BigDecimal totalCalculatedAmount;
    public String environment;
}
