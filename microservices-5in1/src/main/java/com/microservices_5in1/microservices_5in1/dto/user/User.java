package com.microservices_5in1.microservices_5in1.dto.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Validated
//@JsonIgnoreProperties("id")
@JsonFilter("someBeanFilter")
public class User {

    private Integer id;
    @NotBlank
    @Size(min = 2)
    private String name;
    @Past
    private LocalDate birthDate;
}
