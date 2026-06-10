package com.mecash.service.interfaces;

import com.mecash.dto.response.AccountResponse;

public interface AccountService {

    AccountResponse getBalance(String accountNumber);
}
