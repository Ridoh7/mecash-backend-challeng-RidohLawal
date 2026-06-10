package com.mecash.service.interfaces;

import com.mecash.dto.request.TransferRequest;
import com.mecash.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    TransactionResponse transfer(TransferRequest request);

    List<TransactionResponse> getHistory(
            String accountNumber);
}
