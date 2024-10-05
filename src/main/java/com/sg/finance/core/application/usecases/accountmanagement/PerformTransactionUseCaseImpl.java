package com.sg.finance.core.application.usecases.accountmanagement;

import com.sg.finance.core.application.ports.input.PerformTransactionUseCase;
import com.sg.finance.core.application.ports.output.AccountRepository;
import com.sg.finance.core.domain.retailbanking.models.Account;
import com.sg.finance.core.domain.retailbanking.models.TransactionType;
import com.sg.finance.core.domain.retailbanking.services.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Transactional
@Service
@RequiredArgsConstructor
public class PerformTransactionUseCaseImpl implements PerformTransactionUseCase {
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @Override
    public void execute(long accountId, BigDecimal amount, TransactionType transactionType) {
        Account account = accountRepository.findById(accountId);
        accountService.processTransaction(account, amount, transactionType);
        accountRepository.save(account);
    }
}
