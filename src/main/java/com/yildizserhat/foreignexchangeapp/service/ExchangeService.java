package com.yildizserhat.foreignexchangeapp.service;

import com.yildizserhat.foreignexchangeapp.service.dto.ConversionRequestDTO;
import com.yildizserhat.foreignexchangeapp.service.dto.ConversionResponseDTO;
import com.yildizserhat.foreignexchangeapp.service.dto.Currency;
import com.yildizserhat.foreignexchangeapp.service.dto.ExchangeResponseDTO;

public interface ExchangeService {

    ExchangeResponseDTO exchange(Currency source, Currency target);

    ConversionResponseDTO conversion(ConversionRequestDTO requestDTO);
}
