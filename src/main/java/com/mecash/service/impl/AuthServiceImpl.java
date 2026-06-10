package com.mecash.service.impl;

import com.mecash.constants.AppConstants;
import com.mecash.dto.request.LoginRequest;
import com.mecash.dto.request.SignupRequest;
import com.mecash.dto.response.LoginResponse;
import com.mecash.dto.response.SignupResponse;
import com.mecash.entity.Account;
import com.mecash.entity.User;
import com.mecash.enums.CurrencyType;
import com.mecash.exception.BadRequestException;
import com.mecash.repository.AccountRepository;
import com.mecash.repository.UserRepository;
import com.mecash.service.interfaces.AuthService;
import com.mecash.util.AccountNumberGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .build();

        user = userRepository.save(user);

        CurrencyType currency =
                Math.random() < 0.5
                        ? CurrencyType.A
                        : CurrencyType.B;

        Account account = Account.builder()
                .accountNumber(AccountNumberGenerator.generate())
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

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BadRequestException("Invalid credentials"));

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
}