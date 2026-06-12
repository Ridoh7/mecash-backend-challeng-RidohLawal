package com.mecash.service.interfaces;

import com.mecash.dto.response.AccountResponse;

public interface AccountService {

    /**
     * Retrieves the balance and currency information for a given account number.
     *
     * @param accountNumber account number to query
     * @return account balance details
     */
    AccountResponse getBalance(String accountNumber);

}
