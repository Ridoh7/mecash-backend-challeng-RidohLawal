package com.mecash.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Response payload returned when
 * retrieving account details.
 *
 * Contains the account number,
 * currency type, and current balance.
 */
@Data
@Builder
public class AccountResponse {

    private String accountNumber;

    private String currency;

    private BigDecimal balance;
}