package com.bankproj.bankproj.services;

import com.bankproj.bankproj.payload.TransactionRequest;
import com.bankproj.bankproj.payload.TransactionResponse;
import org.springframework.stereotype.Service;

@Service
public interface AccountTransactionService {
    TransactionResponse addTransaction(TransactionRequest transactionRequest);
}
