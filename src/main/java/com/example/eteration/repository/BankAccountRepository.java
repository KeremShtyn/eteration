package com.example.eteration.repository;

import com.example.eteration.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    Optional<BankAccount> findBankAccountByAccountNumber(String accountNumber);
}
