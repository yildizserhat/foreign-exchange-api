package com.yildizserhat.foreignexchangeapp.service;

import com.yildizserhat.foreignexchangeapp.service.dto.Currency;
import com.yildizserhat.foreignexchangeapp.service.dto.ExchangeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class ExchangeRestService extends RestBaseService {

    @Autowired
    @Qualifier("ratesRestTemplate")
    private RestTemplate restTemplate;

    public ExchangeResponseDTO exchange(Currency source, Currency target) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ExchangeResponseDTO responses = get(buildUrl(source, target), entity, ExchangeResponseDTO.class);

        return responses;
    }

    private String buildUrl(Currency from, Currency to) {
        return UriComponentsBuilder.fromHttpUrl(getRequestUrl())
                .queryParam("base", from.toString())
                .queryParam("symbols", to.toString())
                .build()
                .toUriString();
    }

    private String getRequestUrl() {
        return "https://api.ratesapi.io/api/latest";
    }


    @Bean
    public RestTemplate ratesRestTemplate() {
        return new RestTemplate();
    }

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
