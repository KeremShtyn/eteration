package com.example.eteration.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class AccountResponse {

    private String accountNumber;
    private String owner;
    private double balance;
    private LocalDateTime createDate;
    private List<TransactionDetail> transactions;
}
