package com.example.eteration.entity;

import com.example.eteration.util.base.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@SQLDelete(sql = "UPDATE Products SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Entity(name = "Transactions")
@Table(name = "Transactions")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = true)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transaction extends BaseEntity {

    @Column(name = "AMOUNT")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType; // DEPOSIT, WITHDRAWAL, BILL_PAYMENT

    @Column(name = "APPROVAL_CODE", unique = true, nullable = false, updatable = false)
    private String approvalCode;

    public Transaction(double amount, TransactionType transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.approvalCode = UUID.randomUUID().toString();
    }

    public Transaction() {
    }

    public abstract void apply(BankAccount account);

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
