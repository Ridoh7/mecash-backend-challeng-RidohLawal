package com.mecash.controller;

import com.mecash.dto.request.TransferRequest;
import com.mecash.dto.response.TransactionResponse;
import com.mecash.service.interfaces.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles transaction-related operations.
 *
 * Provides endpoints for transferring funds
 * and retrieving transaction history.
 */
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * Transfers funds between accounts.
     *
     * @param request transfer details
     * @return transaction result
     */
    @Operation(summary = "Transfer funds between accounts")
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(@RequestBody TransferRequest request) {

        return ResponseEntity.ok(transactionService.transfer(request));
    }

    /**
     * Retrieves the transaction history
     * for a specified account.
     *
     * @param accountNumber account number
     * @return list of transactions
     */
    @Operation(summary = "Get transaction history")
    @GetMapping("/{accountNumber}/history")
    public ResponseEntity<List<TransactionResponse>> getHistory(@PathVariable String accountNumber) {

        return ResponseEntity.ok(transactionService.getHistory(accountNumber));
    }
}