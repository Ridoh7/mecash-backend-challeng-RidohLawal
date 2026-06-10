package com.mecash.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponse {

    private String email;
    private String accountNumber;
    private String currency;
    private String message;
    private String balance;
}