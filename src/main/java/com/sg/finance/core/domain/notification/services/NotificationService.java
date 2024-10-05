package com.sg.finance.core.domain.notification.services;

import com.sg.finance.core.domain.retailbanking.models.Account;
import com.sg.finance.core.domain.retailbanking.models.TransactionType;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class NotificationService {
    public void notifyUser(Account account, TransactionType transactionType, BigDecimal amount) {
        // Send an email, SMS, or a push notification
        log.info("Sending notification: Account ID: {}, Transaction Type: {}, Amount: {}",
                account.getAccountId(), transactionType, amount);
    }
}
