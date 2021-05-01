package com.yildizserhat.foreignexchangeapp.entity;

import com.yildizserhat.foreignexchangeapp.service.dto.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CONVERSION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conversion {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "SOURCE_AMOUNT")
    private Double sourceAmount;

    @Column(name = "SOURCE_CURRENCY")
    private Currency sourceCurrency;

    @Column(name = "TARGET_CURRENCY")
    private Currency targetCurrency;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
}
