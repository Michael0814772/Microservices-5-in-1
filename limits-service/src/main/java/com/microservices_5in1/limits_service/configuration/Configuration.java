package com.microservices_5in1.limits_service.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limit-service")
@Getter
@Setter
public class Configuration {

    private int minimum;
    private int maximum;
}
