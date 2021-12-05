package com.bankproj.bankproj.payload;

import lombok.Data;

@Data
public class AccountResponse {
    private long BankAccountId;
    private String accountName;
    private float AccountBalance;
}
