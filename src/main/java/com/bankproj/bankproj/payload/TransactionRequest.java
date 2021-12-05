package com.bankproj.bankproj.payload;

import lombok.Data;

@Data
public class TransactionRequest {
    private float transactionAmount;
    private String transactionType;
    private long bankAccountId;
}
