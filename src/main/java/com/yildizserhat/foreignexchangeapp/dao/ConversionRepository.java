package com.yildizserhat.foreignexchangeapp.dao;

import com.yildizserhat.foreignexchangeapp.entity.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public interface ConversionRepository extends JpaRepository<Conversion, Long> {
    Page<Conversion> findByTransactionId(@RequestParam("id") String id, Pageable pageable);

    Page<Conversion> findByTransactionDate(@DateTimeFormat(pattern = "yyyy-MM-dd") @Param("date") Date date, Pageable pageable);
}
