package com.example.eteration.controller;

import com.example.eteration.api.BankAccountApi;
import com.example.eteration.entity.BankAccount;
import com.example.eteration.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class BankAccountController implements BankAccountApi {

    private BankService bankService;


    public BankAccountController(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public BankAccount getById(String id) {
        return bankService.getAccountDetails(id);
    }


    @Override
    public BankAccount createAccount(BankAccount bankAccount) {
        bankService.createAccount(bankAccount);
        return bankAccount;
    }

}
