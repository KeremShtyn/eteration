package com.example.eteration.util;


import com.example.eteration.entity.BankAccount;
import com.example.eteration.entity.DepositTransaction;
import com.example.eteration.entity.PhoneBillPaymentTransaction;
import com.example.eteration.entity.WithdrawalTransaction;
import com.example.eteration.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InitialData implements CommandLineRunner {

    private final BankAccountRepository accountRepository;

    public InitialData(BankAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Bank Account Oluşturma
        BankAccount account = new BankAccount("123-456-789", "John Doe", 1000);

        // Deposit Transaction
        DepositTransaction deposit = new DepositTransaction(500.0);
        deposit.setApprovalCode(UUID.randomUUID().toString());
        account.post(deposit);

        // Withdrawal Transaction
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(200.0);
        withdrawal.setApprovalCode(UUID.randomUUID().toString());
        account.post(withdrawal);

        // Phone Bill Payment Transaction
        PhoneBillPaymentTransaction phoneBill = new PhoneBillPaymentTransaction("Vodafone", "1234567890", 150.0);
        phoneBill.setApprovalCode(UUID.randomUUID().toString());
        account.post(phoneBill);

        // Veriyi Kaydetme
        accountRepository.save(account);

        System.out.println("Initial data loaded!");
    }
}
