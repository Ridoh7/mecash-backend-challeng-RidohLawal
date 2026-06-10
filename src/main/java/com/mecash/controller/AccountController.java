package com.mecash.controller;

import com.mecash.dto.response.AccountResponse;
import com.mecash.service.interfaces.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Get account balance")
    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<AccountResponse> getBalance(
            @PathVariable String accountNumber) {

        return ResponseEntity.ok(
                accountService.getBalance(accountNumber));
    }
}