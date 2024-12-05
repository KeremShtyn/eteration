package com.example.eteration.util.request;

import com.example.eteration.entity.TransactionType;
import lombok.Data;

@Data
public class TransactionRequestDTO {

    private String accountNumber;
    private double amount;
    private TransactionType transactionType;
    private String payee;
    private String taxNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getPayee() {
        return payee;
    }

    public String getTaxNumber() {
        return taxNumber;
    }
}
