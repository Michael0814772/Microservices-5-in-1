package com.microservices_5in1.currency_exchange_service.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemNotFoundException {
    private String responseMsg;
    private String responseCode = "99";
    private LocalDateTime localDateTime = LocalDateTime.now();
}
