package com.bankproj.bankproj.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "varchar(255)")
    private String accountName;

    @Column(columnDefinition = "varchar(255)")
    private float outstandingBalance;

    @OneToMany
    private List<AccountTransaction> accountTransactions;

    public BankAccount() {}

    public BankAccount(long id, String accountName, float outstandingBalance, List<AccountTransaction> accountTransactions) {
        this.id = id;
        this.accountName = accountName;
        this.outstandingBalance = outstandingBalance;
        this.accountTransactions = accountTransactions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public float getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(float outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public List<AccountTransaction> getAccountTransactions() {
        return accountTransactions;
    }

    public void setAccountTransactions(List<AccountTransaction> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }
}
