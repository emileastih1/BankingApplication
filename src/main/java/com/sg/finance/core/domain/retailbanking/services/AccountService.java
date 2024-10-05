package com.sg.finance.core.domain.retailbanking.services;

import com.sg.finance.core.domain.retailbanking.models.Account;
import com.sg.finance.core.domain.retailbanking.models.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    public void processTransaction(Account account, BigDecimal amount, TransactionType transactionType) {

        switch (transactionType){
            case CREDIT -> account.credit(amount);
            case DEBIT -> account.debit(amount);
            case TRANSFER -> account.transfer(amount);
            case HOLD -> account.hold(amount);
        }
        // Additional logic for transaction processing (e.g., logging, notifications)
    }
}
