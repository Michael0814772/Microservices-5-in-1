package com.microservices_5in1.currency_exchange_service.dto;

import lombok.Data;

@Data
public class ResponseDto {

    public int id;
    public String from;
    public String to;
    public double conversionMultiple;
    public String environment;
}
