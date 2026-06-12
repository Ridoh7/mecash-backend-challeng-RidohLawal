package com.mecash.controller;

import com.mecash.dto.response.AccountResponse;
import com.mecash.service.interfaces.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles account-related operations.
 *
 * Provides endpoints for retrieving
 * account information and balances.
 */
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    /**
     * Retrieves the current balance for a specified account.
     *
     * Access is restricted to the authenticated account owner.
     *
     * @param accountNumber account number
     * @return account balance information
     */
    @Operation(summary = "Get account balance")
    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<AccountResponse> getBalance(@PathVariable String accountNumber) {

        return ResponseEntity.ok(accountService.getBalance(accountNumber));
    }
}