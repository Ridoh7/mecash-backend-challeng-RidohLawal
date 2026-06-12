package com.mecash.service.impl;

import com.mecash.dto.response.AccountResponse;
import com.mecash.entity.Account;
import com.mecash.entity.User;
import com.mecash.exception.ResourceNotFoundException;
import com.mecash.repository.AccountRepository;
import com.mecash.repository.UserRepository;
import com.mecash.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public AccountResponse getBalance(String accountNumber) {

        // Retrieve account by account number
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() ->
                                new ResourceNotFoundException("Account not found"));

        // Get currently authenticated user
        User authenticatedUser = getAuthenticatedUser();

        System.out.println("Authenticated User ID: "
                        + authenticatedUser.getId());

        System.out.println("Account Owner ID: "
                        + account.getUser().getId());

        // Security check: users can only view balances belonging to their own account
        if (!account.getUser().getId()
                .equals(authenticatedUser.getId())) {

            throw new AccessDeniedException("You are not authorized to view this account");

        }

        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .currency(account.getCurrency().name())
                .balance(account.getBalance())
                .build();
    }

    /**
     * Retrieves the currently authenticated user
     * from the Spring Security context.
     */
    private User getAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email).orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

    }

}
