package com.example.eteration.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class PhoneBillPaymentTransaction extends Transaction{

    private String payee;

    private String taxNumber;

    public PhoneBillPaymentTransaction() {
        super();
        this.setTransactionType(TransactionType.BILL_PAYMENT);
    }

    public PhoneBillPaymentTransaction(String payee, String taxNumber, double amount) {
        super(amount, TransactionType.BILL_PAYMENT);
        this.taxNumber = taxNumber;
        this.payee = payee;
    }

    @Override
    public void apply(BankAccount account) {
        if (account.getBalance() < getAmount()) {
            throw new IllegalArgumentException("Insufficient balance for phone bill payment");
        }
        account.debit(getAmount());
    }


}
