package com.yildizserhat.foreignexchangeapp.controller;

import com.yildizserhat.foreignexchangeapp.service.dto.ConversionRequestDTO;
import com.yildizserhat.foreignexchangeapp.service.dto.ConversionResponseDTO;
import com.yildizserhat.foreignexchangeapp.service.dto.Currency;
import com.yildizserhat.foreignexchangeapp.service.dto.ExchangeResponseDTO;
import com.yildizserhat.foreignexchangeapp.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/exchange")
    public ResponseEntity<?> exchangeRate(@RequestParam("source") Currency source, @RequestParam("target") Currency target) {

        ExchangeResponseDTO exchange = exchangeService.exchange(source, target);

        return ok(exchange.getRates());
    }

    @GetMapping("/conversion")
    public ResponseEntity<?> conversion(@RequestBody ConversionRequestDTO requestDTO) {

        ConversionResponseDTO conversion = exchangeService.conversion(requestDTO);
        return ok(conversion);
    }
}
