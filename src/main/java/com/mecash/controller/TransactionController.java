package com.mecash.controller;

import com.mecash.dto.request.TransferRequest;
import com.mecash.dto.response.TransactionResponse;
import com.mecash.service.interfaces.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Transfer funds between accounts")
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(
            @RequestBody TransferRequest request) {

        return ResponseEntity.ok(
                transactionService.transfer(request));
    }

    @Operation(summary = "Get transaction history")
    @GetMapping("/{accountNumber}/history")
    public ResponseEntity<List<TransactionResponse>>
    getHistory(
            @PathVariable String accountNumber) {

        return ResponseEntity.ok(
                transactionService.getHistory(
                        accountNumber));
    }
}