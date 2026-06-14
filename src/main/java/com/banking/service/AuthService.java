//package com.banking.service;
//
//import java.math.BigDecimal;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.banking.dto.LoginRequest;
//import com.banking.dto.RegisterRequest;
//import com.banking.model.Account;
//import com.banking.model.User;
//import com.banking.repo.AccountRepository;
//import com.banking.repo.UserRepository;
//import com.banking.security.JwtUtil;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    //  Register
//    public String register(RegisterRequest request) {
//
//        // Check if email already exists
//        if (userRepository.existsByEmail(request.getEmail())) {
//            throw new RuntimeException("Email already exists!");
//        }
//
//        // Create User
//        User user = new User();
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(User.Role.USER);
//        userRepository.save(user);
//
//        // Create Account automatically
//        Account account = new Account();
//        account.setAccountNumber(generateAccountNumber());
//        account.setBalance(BigDecimal.ZERO);
//        account.setUser(user);
//        accountRepository.save(account);
//
//        return "User registered successfully! " +
//               "Account Number: " + account.getAccountNumber();
//    }
//
//   
//    
// //  Login - pass userId to token
//    public String login(LoginRequest request) {
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found!"));
//
//        if (!passwordEncoder.matches(
//                request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid password!");
//        }
//
//        // pass both email and userId
//        return jwtUtil.generateToken(user.getEmail(), user.getId());
//    }
//    
//   
//
//    //  Generate Account Number
//    private String generateAccountNumber() {
//        return "ACC" + UUID.randomUUID()
//                           .toString()
//                           .substring(0, 8)
//                           .toUpperCase();
//    }
//}


package com.banking.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy; // ◄── ADDED THIS IMPORT
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
    @Lazy // ◄── CRITICAL: Stops the password encoder cycle during startup
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy // ◄── ADDED: Keeps the utility components isolated until runtime
    private JwtUtil jwtUtil;

    //  Register
    public String register(RegisterRequest request) {
        // 1. Manual Validation: Check for nulls/invalid types
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("All fields are required");
        }
        
        // 2. Business Rule: Basic format check
        if (request.getName().length() < 2) {
            throw new IllegalArgumentException("Name is too short");
        }

        // 3. Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        // 4. Create and Save User
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.USER);
        userRepository.save(user);

        // 5. Create and Save Account
        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);
        accountRepository.save(account);

        return "User registered successfully! Account Number: " + account.getAccountNumber();
    }

    //  Login - pass userId to token
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(
                request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        // pass both email and userId
        return jwtUtil.generateToken(user.getEmail(), user.getId());
    }

    //  Generate Account Number
    private String generateAccountNumber() {
        return "ACC" + UUID.randomUUID()
                           .toString()
                           .substring(0, 8)
                           .toUpperCase();
    }
}