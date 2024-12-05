package com.example.eteration.entity;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class WithdrawalTransaction extends Transaction{



    public WithdrawalTransaction() {
        super();
        this.setTransactionType(TransactionType.WITHDRAWAL);
    }

    public WithdrawalTransaction(double amount) {
        super(amount, TransactionType.WITHDRAWAL);
    }

    @Override
    public void apply(BankAccount account) {
        if (account.getBalance() < getAmount()) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.debit(getAmount());
    }
}
