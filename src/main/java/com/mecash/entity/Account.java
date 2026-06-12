package com.mecash.entity;

import com.mecash.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Represents a user's account.
 *
 * Each account has a unique account number,
 * a currency type, a balance, and belongs
 * to a registered user.
 */
@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    // Unique account identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique account number used for transfers
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    // Currency assigned to the account
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    // Current account balance
    @Column(nullable = false)
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    // Owner of the account
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}