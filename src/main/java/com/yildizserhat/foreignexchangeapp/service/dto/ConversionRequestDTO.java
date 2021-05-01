package com.yildizserhat.foreignexchangeapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversionRequestDTO {
    private Double sourceAmount;
    private Currency sourceCurrency;
    private Currency targetCurrency;
}
