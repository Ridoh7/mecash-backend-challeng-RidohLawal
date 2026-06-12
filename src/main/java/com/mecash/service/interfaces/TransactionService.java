package com.mecash.service.interfaces;

import com.mecash.dto.request.TransferRequest;
import com.mecash.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    /**
     * Transfers funds between two accounts.
     * Supports currency conversion when the
     * sender and receiver accounts use different currencies.
     *
     * @param request transfer details
     * @return transaction result
     */
    TransactionResponse transfer(TransferRequest request);

    /**
     * Retrieves the transaction history for a given account.
     *
     * @param accountNumber account number to query
     * @return list of transactions associated with the account
     */
    List<TransactionResponse> getHistory(String accountNumber);

}
