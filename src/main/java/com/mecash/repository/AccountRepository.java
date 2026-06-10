package com.mecash.repository;

import com.mecash.entity.Account;
import com.mecash.enums.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    long countByCurrency(CurrencyType currency);
}