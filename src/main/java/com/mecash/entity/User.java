package com.mecash.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Represents an application user.
 *
 * A user can own one account and authenticate using their email address and password.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    // Unique identifier for the user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Optional first name, populated through profile update
    @Column
    private String firstName;

    // Optional last name, populated through profile update
    @Column
    private String lastName;

    // User's unique login email
    @Column(nullable = false, unique = true)
    private String email;

    // Encrypted password stored using BCrypt
    @Column(nullable = false)
    private String password;

    // Timestamp automatically generated when the user is created
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}