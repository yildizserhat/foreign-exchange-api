package com.yildizserhat.foreignexchangeapp.service;

import com.yildizserhat.foreignexchangeapp.exception.ExchangeInfoNotFoundException;
import com.yildizserhat.foreignexchangeapp.service.dto.Currency;
import com.yildizserhat.foreignexchangeapp.service.dto.ExchangeResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeRestServiceTest {

    @InjectMocks
    private ExchangeRestService restService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void shouldExchange() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Date date = new Date();
        ExchangeResponseDTO responseDTO = ExchangeResponseDTO.builder()
                .date(date)
                .build();

        String url = "https://api.ratesapi.io/api/latest?base=TRY&symbols=EUR";

        ResponseEntity<ExchangeResponseDTO> responseEntity = new ResponseEntity<>(responseDTO, HttpStatus.OK);

        when(restTemplate.exchange(url, HttpMethod.GET, entity, ExchangeResponseDTO.class)).thenReturn(responseEntity);

        ExchangeResponseDTO exchange = restService.exchange(Currency.TRY, Currency.EUR);

        assertEquals(date, exchange.getDate());
    }

    @Test(expected = ExchangeInfoNotFoundException.class)
    public void shouldThrowExceptionIfBodyIsNull() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "https://api.ratesapi.io/api/latest?base=TRY&symbols=EUR";

        ResponseEntity<ExchangeResponseDTO> responseEntity = new ResponseEntity<>( HttpStatus.OK);

        when(restTemplate.exchange(url, HttpMethod.GET, entity, ExchangeResponseDTO.class)).thenReturn(responseEntity);

        ExchangeResponseDTO exchange = restService.exchange(Currency.TRY, Currency.EUR);
    }

    @Test(expected = ExchangeInfoNotFoundException.class)
    public void shouldThrowExceptionIfItsNotSuccessful() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Date date = new Date();
        ExchangeResponseDTO responseDTO = ExchangeResponseDTO.builder()
                .date(date)
                .build();

        String url = "https://api.ratesapi.io/api/latest?base=TRY&symbols=EUR";

        ResponseEntity<ExchangeResponseDTO> responseEntity = new ResponseEntity<>( HttpStatus.NOT_FOUND);

        when(restTemplate.exchange(url, HttpMethod.GET, entity, ExchangeResponseDTO.class)).thenReturn(responseEntity);

        ExchangeResponseDTO exchange = restService.exchange(Currency.TRY, Currency.EUR);
    }

}