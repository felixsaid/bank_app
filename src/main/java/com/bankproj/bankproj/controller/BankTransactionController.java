package com.bankproj.bankproj.controller;

import com.bankproj.bankproj.entity.BankAccount;
import com.bankproj.bankproj.exceptions.ResourceNotFoundException;
import com.bankproj.bankproj.payload.AccountRequest;
import com.bankproj.bankproj.payload.AccountResponse;
import com.bankproj.bankproj.payload.TransactionRequest;
import com.bankproj.bankproj.payload.TransactionResponse;
import com.bankproj.bankproj.repository.BankAccountRepository;
import com.bankproj.bankproj.services.AccountTransactionService;
import com.bankproj.bankproj.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BankTransactionController {

    @Autowired
    private AccountTransactionService accountTransactionService;

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/account/create")
    public AccountResponse CreateAccount(@RequestBody AccountRequest accountRequest){
        return bankAccountService.addAccount(accountRequest);
    }

    @GetMapping("/account/balance/{id}")
    public AccountResponse GetAccountBalance(@PathVariable(value = "id") long accountId){
        return bankAccountService.findAccountById(accountId);
    }

    @PostMapping("/transaction")
    public TransactionResponse newTransaction(@RequestBody TransactionRequest transactionRequest) {
        return accountTransactionService.addTransaction(transactionRequest);
    }
}
