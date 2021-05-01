package com.yildizserhat.foreignexchangeapp.exception.handler;

import com.yildizserhat.foreignexchangeapp.exception.ExchangeInfoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExchangeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ExchangeInfoNotFoundException.class})
    public ResponseEntity<?> handle(ExchangeInfoNotFoundException exception) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
