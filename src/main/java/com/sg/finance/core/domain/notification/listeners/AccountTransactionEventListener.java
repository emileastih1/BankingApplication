package com.sg.finance.core.domain.notification.listeners;

import com.sg.finance.core.domain.notification.services.NotificationService;
import com.sg.finance.core.domain.retailbanking.events.AccountTransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountTransactionEventListener {
    private final NotificationService notificationService;

    @EventListener
    public void handleAccountTransactionEvent(AccountTransactionEvent event) {
        log.info("Transaction Event Received: Type: {}, Amount: {}, Account ID: {}",
                event.transactionType(), event.amount(), event.account().getAccountId());

        // Delegate to the NotificationService to send notifications
        notificationService.notifyUser(event.account(), event.transactionType(), event.amount());
    }
}
