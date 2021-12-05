package com.bankproj.bankproj.services.serviceimplementations;

import com.bankproj.bankproj.entity.BankAccount;
import com.bankproj.bankproj.exceptions.ResourceNotFoundException;
import com.bankproj.bankproj.payload.AccountRequest;
import com.bankproj.bankproj.payload.AccountResponse;
import com.bankproj.bankproj.repository.BankAccountRepository;
import com.bankproj.bankproj.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImplementation implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public AccountResponse addAccount(AccountRequest accountRequest) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountName(accountRequest.getAccountName());

        bankAccountRepository.save(bankAccount);

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setBankAccountId(bankAccount.getId());
        accountResponse.setAccountName(bankAccount.getAccountName());
        accountResponse.setAccountBalance(bankAccount.getOutstandingBalance());

        return accountResponse;
    }

    @Override
    public AccountResponse findAccountById(long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account not found with id " + id));

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountBalance(bankAccount.getOutstandingBalance());
        accountResponse.setAccountName(bankAccount.getAccountName());
        accountResponse.setBankAccountId(bankAccount.getId());

        return accountResponse;
    }

}
