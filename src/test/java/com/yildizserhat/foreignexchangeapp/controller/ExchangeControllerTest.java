package com.yildizserhat.foreignexchangeapp.controller;

import com.yildizserhat.foreignexchangeapp.service.ExchangeService;
import com.yildizserhat.foreignexchangeapp.service.dto.ConversionRequestDTO;
import com.yildizserhat.foreignexchangeapp.service.dto.ConversionResponseDTO;
import com.yildizserhat.foreignexchangeapp.service.dto.Currency;
import com.yildizserhat.foreignexchangeapp.service.dto.ExchangeResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeControllerTest {

    @InjectMocks
    private ExchangeController controller;

    @Mock
    private ExchangeService exchangeService;

    @Test
    public void shouldReturnRate() {
        Map<Currency, Double> map = new HashMap<>();
        map.put(Currency.TRY, 800.00);

        ExchangeResponseDTO responseDTO = ExchangeResponseDTO.builder()
                .base("TRY")
                .rates(map)
                .date(new Date())
                .build();
        when(exchangeService.exchange(Currency.USD, Currency.TRY)).thenReturn(responseDTO);

        ResponseEntity<?> responseEntity = controller.exchangeRate(Currency.USD, Currency.TRY);

        assertNotNull(responseEntity.getBody());
        assertEquals(responseEntity.getBody(), map);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    public void shouldReturnConversion() {
        ConversionRequestDTO conversionRequestDTO = ConversionRequestDTO.builder()
                .sourceAmount(100.00)
                .sourceCurrency(Currency.EUR)
                .targetCurrency(Currency.TRY)
                .build();

        ConversionResponseDTO conversionResponseDTO = ConversionResponseDTO.builder()
                .amount(1000.00)
                .id("transactionId")
                .build();

        when(exchangeService.conversion(conversionRequestDTO)).thenReturn(conversionResponseDTO);

        ResponseEntity<?> responseEntity = controller.conversion(conversionRequestDTO);

        assertNotNull(responseEntity.getBody());
        assertEquals(responseEntity.getBody(), conversionResponseDTO);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

}