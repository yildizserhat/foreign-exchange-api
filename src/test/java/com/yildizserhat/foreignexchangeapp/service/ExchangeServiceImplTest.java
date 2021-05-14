package com.yildizserhat.foreignexchangeapp.service;

import com.yildizserhat.foreignexchangeapp.dao.ConversionRepository;
import com.yildizserhat.foreignexchangeapp.service.dto.ConversionRequestDTO;
import com.yildizserhat.foreignexchangeapp.service.dto.ConversionResponseDTO;
import com.yildizserhat.foreignexchangeapp.service.dto.Currency;
import com.yildizserhat.foreignexchangeapp.service.dto.ExchangeResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeServiceImplTest {

    @InjectMocks
    private ExchangeServiceImpl service;

    @Mock
    private ConversionRepository repository;

    @Mock
    private ExchangeRestService restService;

    @Test
    public void shouldReturnExchangeValue() {
        ExchangeResponseDTO exchangeResponseDTO = ExchangeResponseDTO.builder().build();
        when(restService.exchange(Currency.AUD, Currency.CAD)).thenReturn(exchangeResponseDTO);

        ExchangeResponseDTO responseDTO = service.exchange(Currency.AUD, Currency.CAD);

        assertEquals(responseDTO, exchangeResponseDTO);
    }

    @Test
    public void shouldReturnConversionAmount() {
        ConversionRequestDTO conversionRequestDTO = ConversionRequestDTO.builder()
                .sourceAmount(10.0)
                .sourceCurrency(Currency.EUR)
                .targetCurrency(Currency.TRY)
                .build();

        Date date = new Date();

        Map<Currency, Double> map = new HashMap<>();
        map.put(Currency.TRY, 10.0);

        ExchangeResponseDTO exchangeResponseDTO = ExchangeResponseDTO.builder()
                .rates(map)
                .date(date)
                .build();


        when(restService.exchange(conversionRequestDTO.getSourceCurrency(), conversionRequestDTO.getTargetCurrency())).thenReturn(exchangeResponseDTO);

        ConversionResponseDTO responseDTO = service.conversion(conversionRequestDTO);

        assertEquals(responseDTO.getAmount(), 100.0);
        verify(repository).save(any());
    }
}