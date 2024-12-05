package com.example.eteration.api;


import com.example.eteration.entity.BankAccount;
import com.example.eteration.util.request.TransactionRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.eteration.util.api.EterationApiEndpoints.ACCOUNT_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(ACCOUNT_PATH)
public interface BankAccountApi {

    @GetMapping("/{id}")
    public BankAccount getById(@PathVariable("id") String id);


    @PostMapping
    public BankAccount createAccount(@RequestBody BankAccount bankAccount);


}
