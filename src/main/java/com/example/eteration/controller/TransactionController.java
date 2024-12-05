package com.example.eteration.controller;

import com.example.eteration.api.TransactionApi;
import com.example.eteration.entity.Transaction;
import com.example.eteration.service.BankService;
import com.example.eteration.util.request.TransactionRequestDTO;
import com.example.eteration.util.response.TransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class TransactionController implements TransactionApi {

    private BankService bankService;

    public TransactionController(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public ResponseEntity<?> createTransaction(TransactionRequestDTO transactionRequest) {
        Transaction transaction = bankService.createTransaction(transactionRequest);
        return ResponseEntity.ok(new TransactionResponse("Ok", transaction.getApprovalCode()));
    }
}
