package com.yildizserhat.foreignexchangeapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeResponseDTO {
    private String base;
    private Map<Currency, Double> rates = new HashMap<>();
    private Date date;
}
