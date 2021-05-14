package com.yildizserhat.foreignexchangeapp.service;

import com.yildizserhat.foreignexchangeapp.dao.ConversionRepository;
import com.yildizserhat.foreignexchangeapp.entity.Conversion;
import com.yildizserhat.foreignexchangeapp.service.dto.ConversionRequestDTO;
import com.yildizserhat.foreignexchangeapp.service.dto.ConversionResponseDTO;
import com.yildizserhat.foreignexchangeapp.service.dto.Currency;
import com.yildizserhat.foreignexchangeapp.service.dto.ExchangeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeRestService exchangeRestService;

    @Autowired
    private ConversionRepository conversionRepository;

    @Override
    public ExchangeResponseDTO exchange(Currency source, Currency target) {
        return exchangeRestService.exchange(source, target);
    }

    @Override
    public ConversionResponseDTO conversion(ConversionRequestDTO requestDTO) {
        ExchangeResponseDTO exchange = exchangeRestService.exchange(requestDTO.getSourceCurrency(), requestDTO.getTargetCurrency());

        for (Map.Entry<Currency, Double> rate : exchange.getRates().entrySet()) {
            String transactionId = UUID.randomUUID().toString();
            double amount = rate.getValue() * requestDTO.getSourceAmount();

            Conversion conversion = Conversion.builder()
                    .transactionDate(exchange.getDate())
                    .transactionId(transactionId)
                    .sourceAmount(requestDTO.getSourceAmount())
                    .targetCurrency(requestDTO.getTargetCurrency())
                    .sourceCurrency(requestDTO.getSourceCurrency())
                    .amount(amount)
                    .build();

            conversionRepository.save(conversion);
            return ConversionResponseDTO.builder().amount(amount).id(transactionId).build();
        }

        return ConversionResponseDTO.builder().build();
    }
}
