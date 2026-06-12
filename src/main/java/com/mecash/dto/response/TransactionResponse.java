package com.mecash.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Response payload containing
 * transaction details.
 *
 * Includes account information,
 * transferred amounts, exchange rate,
 * transaction status, and timestamp.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private BigDecimal amountSent;
    private BigDecimal amountReceived;
    private BigDecimal exchangeRate;
    private String status;
    private LocalDateTime transactionDate;
}