package com.example.eteration;


import com.example.eteration.entity.BankAccount;
import com.example.eteration.entity.DepositTransaction;
import com.example.eteration.entity.PhoneBillPaymentTransaction;
import com.example.eteration.entity.WithdrawalTransaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {

    @Test
    public void testTransactions() {
        // Hesap oluştur
        BankAccount account = new BankAccount("Jim", "12345", 0);

        // Para yatırma işlemi
        account.post(new DepositTransaction(1000));
        // Para çekme işlemi
        account.post(new WithdrawalTransaction(200));
        // Fatura ödeme işlemi
        account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));

        // Son bakiye kontrolü
        assertEquals(703.50, account.getBalance(), 0.0001);


    }
}
