package com.example.eteration.service;


import com.example.eteration.entity.*;
import com.example.eteration.error.ErrorCodes;
import com.example.eteration.repository.BankAccountRepository;
import com.example.eteration.repository.TransactionRepository;
import com.example.eteration.util.exception.EterationException;
import com.example.eteration.util.request.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
public class BankService {

    public static final TransactionType BILL_PAYMENT = TransactionType.BILL_PAYMENT;
    public static final TransactionType WITHDRAWAL = TransactionType.WITHDRAWAL;
    public static final TransactionType DEPOSIT = TransactionType.DEPOSIT;

    private final BankAccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankService(BankAccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }


    @Transactional
    public Transaction createTransaction(TransactionRequestDTO transactionRequest) {
        BankAccount account = accountRepository.findBankAccountByAccountNumber(transactionRequest.getAccountNumber())
                .orElseThrow(() -> new EterationException(ErrorCodes.DATA_NOT_FOUND));

        this.validateTransactions(transactionRequest, account);
        Transaction transaction = null;

        if (transactionRequest.getTransactionType() == DEPOSIT) {
            transaction = new DepositTransaction(transactionRequest.getAmount());
        } else if (transactionRequest.getTransactionType() == WITHDRAWAL) {
            transaction = new WithdrawalTransaction(transactionRequest.getAmount());
        } else if (transactionRequest.getTransactionType() == BILL_PAYMENT) {
            transaction = new PhoneBillPaymentTransaction(transactionRequest.getPayee(), transactionRequest.getTaxNumber(), transactionRequest.getAmount());
        }

        account.post(transaction);
        accountRepository.save(account);
        return transaction;
    }

    private void validateTransactions(TransactionRequestDTO transactionRequest, BankAccount account){
        if(Objects.isNull(transactionRequest.getTransactionType())){
            throw new EterationException(ErrorCodes.UNKNOWN_TRANSACTION_TYPE);
        }
        if (Objects.isNull(transactionRequest.getAmount()) & transactionRequest.getAmount() <= 0){
            throw new EterationException(ErrorCodes.AMOUNT_CAN_NOT_BE_LOWER_THAN_ZERO);
        }
        if (Objects.isNull(transactionRequest.getAmount()) & transactionRequest.getAmount() > 10000){
            throw new EterationException(ErrorCodes.AMOUNT_CAN_NOT_BE_BIGGER_THAN_TEN_THOUSAND_ON_DEPOSIT);
        }
        if (transactionRequest.getTransactionType() == BILL_PAYMENT & (Objects.isNull(transactionRequest.getPayee()) | Objects.isNull(transactionRequest.getTaxNumber()))){
            throw new EterationException(ErrorCodes.PAYEE_OR_TAX_NUMBER_CAN_NOT_BE_EMPTY);
        }
        if (transactionRequest.getTransactionType() == WITHDRAWAL & account.getBalance() < transactionRequest.getAmount()){
            throw new EterationException(ErrorCodes.BALANCE_IS_NOT_ENOUGH);
        }


    }


    @Transactional(readOnly = true)
    public BankAccount getAccountDetails(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EterationException(ErrorCodes.DATA_NOT_FOUND));
    }

    public BankAccount createAccount(BankAccount bankAccount){
        return accountRepository.save(bankAccount);
    }
}
