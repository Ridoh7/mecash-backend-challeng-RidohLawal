package com.mecash.repository;

import com.mecash.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for User entity operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by email address.
     *
     * Used during authentication and profile management operations.
     *
     * @param email user email address
     * @return matching user if found
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks whether a user with the specified email already exists.
     *
     * Used during registration to prevent duplicate accounts.
     *
     * @param email email address to check
     * @return true if the email already exists
     */
    boolean existsByEmail(String email);
}