package com.mecash.entity;

import com.mecash.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a fund transfer between two accounts.
 *
 * Each transaction records the sender account,
 * receiver account, transferred amounts,
 * exchange rate applied, status, and timestamp.
 */
@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    // Unique transaction identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Account initiating the transfer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    // Account receiving the transfer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_account_id")
    private Account receiverAccount;

    // Amount deducted from sender's account
    @Column(name = "amount_sent")
    private BigDecimal amountSent;

    // Amount credited to receiver's account
    // after currency conversion (if applicable)
    @Column(name = "amount_received")
    private BigDecimal amountReceived;

    // Exchange rate used for the transfer
    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    // Current transaction outcome
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    // Timestamp automatically generated when
    // the transaction is created
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}