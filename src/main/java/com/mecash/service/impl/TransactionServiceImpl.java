package com.mecash.service.impl;

import com.mecash.constants.AppConstants;
import com.mecash.dto.request.TransferRequest;
import com.mecash.dto.response.TransactionResponse;
import com.mecash.entity.Account;
import com.mecash.entity.Transaction;
import com.mecash.entity.User;
import com.mecash.enums.CurrencyType;
import com.mecash.enums.TransactionStatus;
import com.mecash.exception.BadRequestException;
import com.mecash.exception.ResourceNotFoundException;
import com.mecash.repository.AccountRepository;
import com.mecash.repository.TransactionRepository;
import com.mecash.repository.UserRepository;
import com.mecash.service.interfaces.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public TransactionResponse transfer(
            TransferRequest request) {

        // Retrieve sender account
        Account sender = accountRepository.findByAccountNumber(
                        request.getSenderAccountNumber()).orElseThrow(() ->
                        new BadRequestException("Sender account not found"));

        // Security check: authenticated user can only transfer
        // funds from accounts they own
        User authenticatedUser = getAuthenticatedUser();

        if (!sender.getUser().getId().equals(authenticatedUser.getId())) {

            throw new AccessDeniedException("You can only transfer from your own account");

        }

        // Retrieve receiver account
        Account receiver = accountRepository.findByAccountNumber(request.getReceiverAccountNumber()).orElseThrow(() ->
                new BadRequestException("Receiver account not found"));

        BigDecimal amountSent = request.getAmount();

        // Validate transfer amount
        if (amountSent.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be greater than zero");
        }

        // Ensure sender has enough balance
        if (sender.getBalance().compareTo(amountSent) < 0) {
            throw new BadRequestException("Insufficient balance");
        }

        BigDecimal exchangeRate = BigDecimal.ONE;
        BigDecimal amountReceived = amountSent;

        // Apply currency conversion when accounts use different currencies
        if (sender.getCurrency() != receiver.getCurrency()) {

            if (sender.getCurrency() == CurrencyType.A
                    && receiver.getCurrency() == CurrencyType.B) {

                exchangeRate = AppConstants.EXCHANGE_RATE;

                amountReceived = amountSent.multiply(exchangeRate)
                                .setScale(2, RoundingMode.HALF_UP);

            } else {

                exchangeRate = BigDecimal.ONE.divide(
                        AppConstants.EXCHANGE_RATE, 6, RoundingMode.HALF_UP);

                amountReceived = amountSent.multiply(exchangeRate)
                                .setScale(2, RoundingMode.HALF_UP);
            }
        }

        // Update account balances
        sender.setBalance(
                sender.getBalance().subtract(amountSent));

        receiver.setBalance(
                receiver.getBalance().add(amountReceived));

        accountRepository.save(sender);
        accountRepository.save(receiver);

        // Record successful transaction
        Transaction transaction = Transaction.builder()
                        .senderAccount(sender)
                        .receiverAccount(receiver)
                        .amountSent(amountSent)
                        .amountReceived(amountReceived)
                        .exchangeRate(exchangeRate)
                        .status(TransactionStatus.SUCCESS)
                        .createdAt(LocalDateTime.now())
                        .build();

        transactionRepository.save(transaction);

        return TransactionResponse.builder()
                .senderAccountNumber(sender.getAccountNumber())
                .receiverAccountNumber(receiver.getAccountNumber())
                .amountSent(amountSent)
                .amountReceived(amountReceived)
                .exchangeRate(exchangeRate)
                .status("SUCCESS")
                .transactionDate(transaction.getCreatedAt())
                .build();
    }

    @Override
    @Transactional
    public List<TransactionResponse> getHistory(
            String accountNumber) {

        // Retrieve account whose history is being requested
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() ->
                                new ResourceNotFoundException("Account not found"));

        // Security check: users can only view
        // their own transaction history
        User authenticatedUser = getAuthenticatedUser();

        if (!account.getUser().getId().equals(authenticatedUser.getId())) {

            throw new AccessDeniedException("You are not authorized to view this history");
        }

        return transactionRepository
                .findBySenderAccount_AccountNumberOrReceiverAccount_AccountNumber(accountNumber, accountNumber)
                .stream()
                .map(transaction -> TransactionResponse.builder()
                        .senderAccountNumber(transaction.getSenderAccount().getAccountNumber())
                        .receiverAccountNumber(transaction.getReceiverAccount().getAccountNumber())
                        .amountSent(transaction.getAmountSent())
                        .amountReceived(transaction.getAmountReceived())
                        .exchangeRate(transaction.getExchangeRate())
                        .status(transaction.getStatus().name())
                        .transactionDate(transaction.getCreatedAt())
                        .build())
                .toList();
    }

    /**
     * Retrieves the currently authenticated user
     * from the Spring Security context.
     */
    private User getAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        System.out.println(
                "Authenticated user: " + email);

        return userRepository.findByEmail(email).orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));


    }
}
