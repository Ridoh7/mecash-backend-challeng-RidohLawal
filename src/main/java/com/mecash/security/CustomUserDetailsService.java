package com.mecash.security;

import com.mecash.entity.User;
import com.mecash.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of Spring Security's
 * UserDetailsService.
 *
 * This service loads user information from the database
 * during authentication.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Loads a user by email address.
     *
     * Spring Security automatically invokes this method during Basic Authentication.
     *
     * @param email user's email address
     * @return authenticated user details
     * @throws UsernameNotFoundException if user does not exist
     */
    @Override
    public UserDetails loadUserByUsername(
            String email)
            throws UsernameNotFoundException {

        // Retrieve user from database
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found"));

        // Convert application User entity into
        // Spring Security UserDetails object
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}