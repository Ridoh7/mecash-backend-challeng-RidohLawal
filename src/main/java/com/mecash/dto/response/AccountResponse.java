package com.mecash.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountResponse {

    private String accountNumber;
    private String currency;
    private BigDecimal balance;
}