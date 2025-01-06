package com.microservices_5in1.currency_conversion_service.feignProxy;

import com.microservices_5in1.currency_conversion_service.dto.CurrencyConversionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

//@FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionDto retrieveExchangeValue(
            @PathVariable(value = "from") String from,
            @PathVariable(value = "to") String to
    );
}
