package com.microservices_5in1.microservices_5in1.Utils;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.microservices_5in1.microservices_5in1.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DynamicFiltering {

    public MappingJacksonValue seanBeanFiltering(List<UserEntity> users, String value1, String value2) {
        log.info("Filtering users - {}, by {} and {}", users, value1, value2);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(value1, value2);

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("someBeanFilter", filter);

        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}
