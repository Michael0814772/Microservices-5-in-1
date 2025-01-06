package com.microservices_5in1.currency_exchange_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices_5in1.currency_exchange_service.dto.CurrencyExchangeDto;
import com.microservices_5in1.currency_exchange_service.entity.CurrencyExchangeEntity;
import com.microservices_5in1.currency_exchange_service.exception.MyItemNotFoundException;
import com.microservices_5in1.currency_exchange_service.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final ObjectMapper objectMapper;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchangeDto retrieveExchangeValue(
            @PathVariable(value = "from") String from,
            @PathVariable(value = "to") String to
    ) {
        CurrencyExchangeEntity currencyExchangeEntity = currencyExchangeRepository.findByFromAndTo(from, to);

        if (currencyExchangeEntity == null) {
            throw new MyItemNotFoundException("Item not found for currency exchange: " + from + " - " + to);
        }

        CurrencyExchangeDto currencyExchangeDto = objectMapper.convertValue(currencyExchangeEntity, CurrencyExchangeDto.class);

        currencyExchangeDto.setEnvironment(environment.getProperty("local.server.port"));

        return currencyExchangeDto;
    }
}
