package com.mecash.repository;

import com.mecash.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySenderAccountId(
            Long accountId);

    List<Transaction> findByReceiverAccountId(
            Long accountId);

    List<Transaction>
    findBySenderAccount_AccountNumberOrReceiverAccount_AccountNumber(
            String senderAccountNumber,
            String receiverAccountNumber);
}