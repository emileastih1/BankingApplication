package com.sg.finance.core.domain.retailbanking.models;

import com.sg.finance.core.domain.retailbanking.events.AccountTransactionEvent;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
    private Set<Transaction> transactions = new HashSet<>();


    public Account debit(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance = balance.subtract(amount);
        registerEvent(new AccountTransactionEvent(this, amount, TransactionType.DEBIT));
        return this;
    }

    public Account credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        balance = balance.add(amount);
        registerEvent(new AccountTransactionEvent(this, amount, TransactionType.CREDIT));
        return this;
    }

    public void transfer(BigDecimal amount) {
        //TODO
        registerEvent(new AccountTransactionEvent(this, amount, TransactionType.TRANSFER));
    }

    public void hold(BigDecimal amount) {
        //TODO
        registerEvent(new AccountTransactionEvent(this, amount, TransactionType.HOLD));
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
        transaction.setAccount(null);
    }


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

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private BigDecimal amount;

    @Column(name = "transaction_type")
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

}

