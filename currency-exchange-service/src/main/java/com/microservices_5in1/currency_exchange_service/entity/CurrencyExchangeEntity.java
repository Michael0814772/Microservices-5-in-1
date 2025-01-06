package com.microservices_5in1.currency_exchange_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "currency_exchange")
public class CurrencyExchangeEntity {

    @Id
    @SequenceGenerator(
            name = "currency_exchange_sequence",
            sequenceName = "currency_exchange_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "currency_exchange_sequence"
    )
    public Long id;

    @Column(name = "from_currency", nullable = false)
    public String from;

    @Column(name = "to_currency", nullable = false)
    public String to;

    @Column(name = "conversion_multiple", nullable = false)
    public BigDecimal conversionMultiple;

    @Column(name = "environment", nullable = false)
    public String environment;
}
