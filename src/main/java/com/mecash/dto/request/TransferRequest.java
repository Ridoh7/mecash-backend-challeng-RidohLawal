package com.mecash.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Request payload for transferring funds
 * between two accounts.
 *
 * The transfer amount must be greater than zero.
 */
@Data
public class TransferRequest {

    @NotBlank
    private String senderAccountNumber;

    @NotBlank
    private String receiverAccountNumber;

    @DecimalMin(value = "0.01")
    private BigDecimal amount;
}