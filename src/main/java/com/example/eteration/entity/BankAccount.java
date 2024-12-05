package com.example.eteration.entity;


import com.example.eteration.util.base.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SQLDelete(sql = "UPDATE Products SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Entity(name = "Accounts")
@Table(name = "Accounts", indexes = {@Index(columnList = "ACCOUNT_NUMBER", name = "account_number_indx")})
@EqualsAndHashCode(callSuper = false)
public class BankAccount extends BaseEntity {

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "BALANCE")
    private double balance;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public BankAccount() {
    }

    public BankAccount(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public void debit(double amount) {
        this.balance -= amount;
    }

    public void post(Transaction transaction) {
        transaction.apply(this);
        transactions.add(transaction);
        transaction.setBankAccount(this);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        String transactionsString = transactions.stream()
                .map(Transaction::toString)
                .collect(Collectors.joining(", ", "[", "]"));

        return "BankAccount{" +
                "owner='" + owner + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactionsString +
                '}';
    }

}
