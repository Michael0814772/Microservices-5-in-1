package com.microservices_5in1.currency_exchange_service.repository;

import com.microservices_5in1.currency_exchange_service.entity.CurrencyExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeEntity, Long> {

    @Query("SELECT c FROM CurrencyExchangeEntity c WHERE c.from = :from AND c.to = :to")
    CurrencyExchangeEntity findByFromAndTo(@Param("from") String fromCurrency, @Param("to") String toCurrency);
}
