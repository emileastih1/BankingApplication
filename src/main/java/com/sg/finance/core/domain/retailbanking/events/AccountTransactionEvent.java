package com.sg.finance.core.domain.retailbanking.events;

import com.sg.finance.core.domain.retailbanking.models.Account;
import com.sg.finance.core.domain.retailbanking.models.TransactionType;

import java.math.BigDecimal;

public record AccountTransactionEvent(Account account, BigDecimal amount, TransactionType transactionType) {
}
