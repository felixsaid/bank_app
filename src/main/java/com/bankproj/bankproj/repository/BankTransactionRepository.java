package com.bankproj.bankproj.repository;

import com.bankproj.bankproj.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BankTransactionRepository extends JpaRepository<AccountTransaction, Long> {
    List<AccountTransaction> findByBankAccountIdAndTransactionDateAndTransactionType(long id, LocalDate transactionDate, String transactionType);
}
