package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.model.Account;
import com.banking.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/account")
@Tag(name = "Account", description = "Account management APIs")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // ✅ Get Account by User ID
    @Operation(summary = "Get account by user ID")
    @GetMapping("/user/{userId}")
    public ResponseEntity<Account> getAccountByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAccountByUserId(userId));
    }

    // ✅ Get Account by Account Number
    @Operation(summary = "Get account by account number")
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(
            @PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByNumber(accountNumber));
    }
}