package com.sg.finance.core.application.ports.input;

import com.sg.finance.core.domain.retailbanking.models.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PerformTransactionUseCase {
    void execute(long accountId, BigDecimal amount, TransactionType transactionType);
}
