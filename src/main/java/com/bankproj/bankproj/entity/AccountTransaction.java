package com.bankproj.bankproj.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float transactionAmount;
    private LocalDate transactionDate;
    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    public AccountTransaction() {}

    public AccountTransaction(long id, float transactionAmount, LocalDate transactionDate, String transactionType, BankAccount bankAccount) {
        this.id = id;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.bankAccount = bankAccount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
