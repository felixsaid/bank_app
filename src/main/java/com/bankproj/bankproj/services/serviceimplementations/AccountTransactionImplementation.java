package com.bankproj.bankproj.services.serviceimplementations;

import com.bankproj.bankproj.entity.AccountTransaction;
import com.bankproj.bankproj.entity.BankAccount;
import com.bankproj.bankproj.exceptions.ResourceNotFoundException;
import com.bankproj.bankproj.exceptions.TransactionNotAllowedException;
import com.bankproj.bankproj.payload.TransactionRequest;
import com.bankproj.bankproj.payload.TransactionResponse;
import com.bankproj.bankproj.repository.BankAccountRepository;
import com.bankproj.bankproj.repository.BankTransactionRepository;
import com.bankproj.bankproj.services.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.bankproj.bankproj.utils.AppConstants.*;

@Service
public class AccountTransactionImplementation implements AccountTransactionService {

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public TransactionResponse addTransaction(TransactionRequest transactionRequest) {
        LocalDate date = LocalDate.now();
        long totalDailyTransSum = 0;
        long dailyTransactions = 0;

        //get bank account

        BankAccount bankAccount = bankAccountRepository.findById(transactionRequest.getBankAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Bank account not found with id " + transactionRequest.getBankAccountId()));


        //get a list of the daily transactions by date and transaction type

        List<AccountTransaction> dailyTransactionTotal = bankTransactionRepository
                .findByBankAccountIdAndTransactionDateAndTransactionType(transactionRequest.getBankAccountId(), date, transactionRequest.getTransactionType().toLowerCase());

        // get the total number of daily transactions by date today

        dailyTransactions = dailyTransactionTotal.size();


        // get the sum from all transactions by date today

        for (int i = 0; i < dailyTransactions; i++) {
            totalDailyTransSum+= dailyTransactionTotal.get(i).getTransactionAmount();
        }


        AccountTransaction transaction = new AccountTransaction();
        AccountTransaction newTransaction;

        String transactionType = transactionRequest.getTransactionType();
        TransactionResponse transactionResponse;

        switch (transactionType.toLowerCase()){
            case "deposit" :
                if(transactionRequest.getTransactionAmount() > MAX_DEPOSIT_PER_TRANS)
                    throw new TransactionNotAllowedException("Exceeded Maximum Deposit Amount Per Transaction");


                if(dailyTransactions >= MAX_DEPOSIT_FREQUENCY)
                    throw new TransactionNotAllowedException("Exceeded Maximum Daily Deposit Transactions");


                if(totalDailyTransSum > MAX_DAILY_DEPOSIT_AMOUNT)
                    throw new TransactionNotAllowedException("Exceeded Maximum Daily Deposit Amount");


                transaction.setTransactionAmount(transactionRequest.getTransactionAmount());
                transaction.setTransactionType("deposit".toLowerCase());
                transaction.setBankAccount(bankAccount);
                transaction.setTransactionDate(date);
                newTransaction = bankTransactionRepository.save(transaction);

                //update the account balance
                bankAccount.setOutstandingBalance(bankAccount.getOutstandingBalance() + transaction.getTransactionAmount());
                bankAccountRepository.save(bankAccount);

                transactionResponse = new TransactionResponse();
                transactionResponse.setTransactionAmount(newTransaction.getTransactionAmount());
                transactionResponse.setTransactionType(newTransaction.getTransactionType());

                break;

            case "withdrawal" :
                if(bankAccount.getOutstandingBalance() < transactionRequest.getTransactionAmount())
                    throw new TransactionNotAllowedException("Amount to withdraw exceeds the account balance");


                if(transactionRequest.getTransactionAmount() > MAX_WITHDRAWAL_PER_TRANS)
                    throw new TransactionNotAllowedException("Exceeded Maximum Withdrawal Amount Per Transaction");



                if(dailyTransactions >= MAX_WITHDRAWAL_FREQUENCY)
                    throw new TransactionNotAllowedException("Exceeded Maximum Daily Withdrawal Transactions");


                if(totalDailyTransSum > MAX_DAILY_WITHDRAWAL_AMOUNT)
                    throw new TransactionNotAllowedException("Exceeded Maximum Daily Withdrawal Amount");

                transaction.setTransactionAmount(transactionRequest.getTransactionAmount());
                transaction.setTransactionType("withdrawal".toLowerCase());
                transaction.setBankAccount(bankAccount);
                transaction.setTransactionDate(date);
                newTransaction = bankTransactionRepository.save(transaction);

                //update the account balance
                bankAccount.setOutstandingBalance(bankAccount.getOutstandingBalance() - transaction.getTransactionAmount());
                bankAccountRepository.save(bankAccount);

                transactionResponse = new TransactionResponse();
                transactionResponse.setTransactionAmount(newTransaction.getTransactionAmount());
                transactionResponse.setTransactionType(newTransaction.getTransactionType());

                break;

            default:
                return  null;
        }

        return transactionResponse;
    }
}
