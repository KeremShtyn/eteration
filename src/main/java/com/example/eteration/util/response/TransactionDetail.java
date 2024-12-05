package com.example.eteration.util.response;

import com.example.eteration.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionDetail {

    private LocalDateTime date;
    private double amount;
    private TransactionType transactionType;
    private String approvalCode;
}
