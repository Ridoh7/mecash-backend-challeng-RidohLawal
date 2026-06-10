package com.mecash.service.impl;

import com.mecash.dto.response.AccountResponse;
import com.mecash.entity.Account;
import com.mecash.exception.ResourceNotFoundException;
import com.mecash.repository.AccountRepository;
import com.mecash.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountResponse getBalance(String accountNumber) {

        Account account =
                accountRepository
                        .findByAccountNumber(accountNumber)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Account not found"));

        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .currency(account.getCurrency().name())
                .balance(account.getBalance())
                .build();
    }
}