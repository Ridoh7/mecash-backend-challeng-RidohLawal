package com.mecash.repository;

import com.mecash.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for Account entity operations.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Finds an account using its account number.
     *
     * @param accountNumber account number to search for
     * @return matching account if found
     */
    Optional<Account> findByAccountNumber(String accountNumber);

    /**
     * Checks whether an account number already exists.
     *
     * Used during account creation to reduce the
     * possibility of generating duplicate account numbers.
     *
     * @param accountNumber account number to check
     * @return true if the account number exists
     */
    boolean existsByAccountNumber(String accountNumber);
}