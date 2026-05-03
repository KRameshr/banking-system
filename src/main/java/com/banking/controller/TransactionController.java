package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.TransactionRequest;
import com.banking.model.Transaction;
import com.banking.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transaction")
@Tag(name = "Transaction", description = "Transaction management APIs")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // ✅ Deposit
    @Operation(summary = "Deposit money")
    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<String> deposit(
            @PathVariable Long accountId,
            @Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(
                transactionService.deposit(accountId, request));
    }

    // ✅ Withdraw
    @Operation(summary = "Withdraw money")
    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<String> withdraw(
            @PathVariable Long accountId,
            @Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(
                transactionService.withdraw(accountId, request));
    }

    // ✅ Transfer
    @Operation(summary = "Transfer money to another account")
    @PostMapping("/transfer/{fromAccountId}")
    public ResponseEntity<String> transfer(
            @PathVariable Long fromAccountId,
            @Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(
                transactionService.transfer(fromAccountId, request));
    }

    // ✅ Transaction History
    @Operation(summary = "Get transaction history")
    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<Transaction>> getHistory(
            @PathVariable Long accountId) {
        return ResponseEntity.ok(
                transactionService.getTransactionHistory(accountId));
    }
}