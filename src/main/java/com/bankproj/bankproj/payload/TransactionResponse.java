package com.bankproj.bankproj.payload;

import lombok.Data;

@Data
public class TransactionResponse {
    private String transactionType;
    private float transactionAmount;
}
