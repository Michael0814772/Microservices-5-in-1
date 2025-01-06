package com.microservices_5in1.currency_conversion_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices_5in1.currency_conversion_service.dto.CurrencyConversionDto;
import com.microservices_5in1.currency_conversion_service.feignProxy.CurrencyExchangeProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

    private final CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionDto calculateCurrencyConversion(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") BigDecimal quantity
    ) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionDto> conversionDtoResponseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionDto.class, uriVariables);

        CurrencyConversionDto currencyConversionDto = conversionDtoResponseEntity.getBody();

        return new CurrencyConversionDto(currencyConversionDto.getId(), from, to, quantity,
                currencyConversionDto.getConversionMultiple(), quantity.multiply(currencyConversionDto.getConversionMultiple()), currencyConversionDto.getEnvironment());
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionDto calculateCurrencyConversionFeign(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") BigDecimal quantity
    ) {
        CurrencyConversionDto currencyConversionDto = currencyExchangeProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversionDto(currencyConversionDto.getId(), from, to, quantity,
                currencyConversionDto.getConversionMultiple(), quantity.multiply(currencyConversionDto.getConversionMultiple()), currencyConversionDto.getEnvironment());
    }
}
