package com.mecash.repository;

import com.mecash.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Transaction entity operations.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Retrieves all transactions where the specified account participated as either the sender or receiver.
     *
     * Used to generate a complete transaction history for an account.
     *
     * @param senderAccountNumber sender account number
     * @param receiverAccountNumber receiver account number
     * @return list of matching transactions
     */
    List<Transaction> findBySenderAccount_AccountNumberOrReceiverAccount_AccountNumber(
            String senderAccountNumber,
            String receiverAccountNumber);
}