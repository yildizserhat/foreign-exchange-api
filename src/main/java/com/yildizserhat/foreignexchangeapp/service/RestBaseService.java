package com.yildizserhat.foreignexchangeapp.service;

import com.yildizserhat.foreignexchangeapp.exception.ExchangeInfoNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
public abstract class RestBaseService {

    protected <T> T get(String url, HttpEntity httpEntity, Class responseClazz) {
        log.info("Url: {}, RequestEntity: {}", url, httpEntity);
        ResponseEntity<T> responseEntity = getRestTemplate().exchange(url, GET, httpEntity, responseClazz);
        log.info("ResponseEntity: {}", responseEntity);

        checkResponse(responseEntity);

        return responseEntity.getBody();
    }

    private <T> void checkResponse(ResponseEntity<T> responseEntity) {
        if (Objects.isNull(responseEntity.getBody())) {
            throw new ExchangeInfoNotFoundException("Error occured, response body is null.");
        }

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.error("Error occured when request initiated. Error {}:", responseEntity.getBody());
            throw new ExchangeInfoNotFoundException("Error occured when request initiated");
        }
    }

    public abstract RestTemplate getRestTemplate();
}
