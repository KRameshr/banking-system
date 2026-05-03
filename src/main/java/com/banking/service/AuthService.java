package com.banking.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking.dto.LoginRequest;
import com.banking.dto.RegisterRequest;
import com.banking.model.Account;
import com.banking.model.User;
import com.banking.repo.AccountRepository;
import com.banking.repo.UserRepository;
import com.banking.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Register
    public String register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        // Create User
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.USER);
        userRepository.save(user);

        // Create Account automatically
        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);
        accountRepository.save(account);

        return "User registered successfully! " +
               "Account Number: " + account.getAccountNumber();
    }

    // ✅ Login
    public String login(LoginRequest request) {

        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        // Generate JWT token
        return jwtUtil.generateToken(user.getEmail());
    }

    // ✅ Generate Account Number
    private String generateAccountNumber() {
        return "ACC" + UUID.randomUUID()
                           .toString()
                           .substring(0, 8)
                           .toUpperCase();
    }
}