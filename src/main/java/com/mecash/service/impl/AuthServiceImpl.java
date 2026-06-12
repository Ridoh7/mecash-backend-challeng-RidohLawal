package com.mecash.service.impl;

import com.mecash.constants.AppConstants;
import com.mecash.dto.request.LoginRequest;
import com.mecash.dto.request.SignupRequest;
import com.mecash.dto.request.UpdateProfileRequest;
import com.mecash.dto.response.LoginResponse;
import com.mecash.dto.response.SignupResponse;
import com.mecash.dto.response.UserProfileResponse;
import com.mecash.entity.Account;
import com.mecash.entity.User;
import com.mecash.enums.CurrencyType;
import com.mecash.exception.BadRequestException;
import com.mecash.exception.ResourceNotFoundException;
import com.mecash.repository.AccountRepository;
import com.mecash.repository.UserRepository;
import com.mecash.service.interfaces.AuthService;
import com.mecash.util.AccountNumberGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public SignupResponse signup(SignupRequest request) {

        // Prevent duplicate account registration
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        // Store password in encrypted form using BCrypt
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .build();

        user = userRepository.save(user);

        // Randomly assign Currency A or B as specified
        // in the assessment requirements
        CurrencyType currency = Math.random() < 0.5
                        ? CurrencyType.A
                        : CurrencyType.B;

        // Create a default account with opening balance
        Account account = Account.builder()
                .accountNumber(generateUniqueAccountNumber())
                .currency(currency)
                .balance(AppConstants.OPENING_BALANCE)
                .user(user)
                .build();

        account = accountRepository.save(account);

        return SignupResponse.builder()
                .message("User created successfully")
                .email(user.getEmail())
                .accountNumber(account.getAccountNumber())
                .balance(String.valueOf(account.getBalance()))
                .currency(currency.name())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        // Verify user exists
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                        new BadRequestException("Invalid credentials"));

        // Verify supplied password matches encrypted password
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new BadRequestException("Invalid credentials");
        }

        return LoginResponse.builder()
                .email(user.getEmail())
                .message("Login successful")
                .build();
    }

    @Override
    @Transactional
    public UserProfileResponse updateProfile(String email, UpdateProfileRequest request) {

        // Retrieve user profile
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("User not found"));

        // Get currently authenticated user
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String loggedInEmail = authentication.getName();

        // Security check: users can only update
        // their own profile information
        if (!loggedInEmail.equals(email)) {
            throw new AccessDeniedException("You can only update your own profile");
        }

        // Update profile details
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        userRepository.save(user);

        return UserProfileResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    /**
     * Generates a unique account number.
     *
     * The generated value is checked against the database
     * before being assigned to a new account. The database
     * unique constraint remains the final safeguard against
     * duplicate account numbers.
     *
     * @return unique account number
     */
    private String generateUniqueAccountNumber() {

        String accountNumber;

        do {
            accountNumber = AccountNumberGenerator.generate();
        } while (accountRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }
}