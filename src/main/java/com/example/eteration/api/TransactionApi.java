package com.example.eteration.api;

import com.example.eteration.util.request.TransactionRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.eteration.util.api.EterationApiEndpoints.TRANSACTION_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(TRANSACTION_PATH)
public interface TransactionApi {


    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequestDTO requestDTO);
}
