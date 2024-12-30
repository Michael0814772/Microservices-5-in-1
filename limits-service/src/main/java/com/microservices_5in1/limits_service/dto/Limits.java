package com.microservices_5in1.limits_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Limits {

    private int minimum;
    private int maximum;
}
