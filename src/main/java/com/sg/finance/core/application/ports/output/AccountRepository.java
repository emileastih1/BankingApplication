package com.sg.finance.core.application.ports.output;

import com.sg.finance.core.domain.models.retailbanking.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface AccountRepository extends CommonBaseRepository<Account, Long> {
    List<Account> findByAccountNumber(String AccountNumber);
    List<Account> findAll(Specification<Account> spec, Sort sort);
}
