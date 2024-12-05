package com.example.eteration.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = false)
public class DepositTransaction extends Transaction{

    public DepositTransaction() {
        super();
        this.setTransactionType(TransactionType.DEPOSIT);
    }

    public DepositTransaction(double amount) {
        super(amount, TransactionType.DEPOSIT);
    }

    @Override
    public void apply(BankAccount account) {
        account.credit(getAmount());
    }


}
