package com.sg.finance.core.domain.models.retailbanking;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Account extends AbstractAggregateRoot<Account> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountId;

    @Column(name = "account_number")
    private String accountNumber;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Transaction> transactions;

}

enum AccountStatus{
    ACTIVE,
    INACTIVE,
    SUSPENDED,
    CLOSED
}

//Here we are specifying that the transaction cannot live outside the account aggregate
@Entity(name = "transactions")
class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private BigDecimal amount;

    @Column(name = "transaction_type")
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
}

enum TransactionType{
    CREDIT,
    DEBIT,
    HOLD,
    TRANSFER
}