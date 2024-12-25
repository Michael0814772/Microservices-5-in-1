package com.microservices_5in1.microservices_5in1.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotFoundException {
    private String responseMsg;
    private String responseCode = "99";
    private LocalDateTime localDateTime = LocalDateTime.now();
}
