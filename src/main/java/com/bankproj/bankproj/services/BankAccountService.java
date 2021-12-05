package com.bankproj.bankproj.services;

import com.bankproj.bankproj.entity.BankAccount;
import com.bankproj.bankproj.payload.AccountRequest;
import com.bankproj.bankproj.payload.AccountResponse;
import org.springframework.stereotype.Service;

@Service
public interface BankAccountService {
    AccountResponse addAccount(AccountRequest accountRequest);
    AccountResponse findAccountById(long id);
}
