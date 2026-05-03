package com.banking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dto.TransactionRequest;
import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.repo.AccountRepository;
import com.banking.repo.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    // ✅ Deposit Money
    public String deposit(Long accountId, TransactionRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found!"));

        // Add balance
        account.setBalance(account.getBalance().add(request.getAmount()));
        accountRepository.save(account);

        // Save transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setType(Transaction.TransactionType.CREDIT);
        transaction.setDescription(request.getDescription());
        transaction.setDate(LocalDateTime.now());
        transaction.setAccount(account);
        transactionRepository.save(transaction);

        return "Deposited successfully! New Balance: " + account.getBalance();
    }

    // ✅ Withdraw Money
    public String withdraw(Long accountId, TransactionRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found!"));

        // Check balance
        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance!");
        }

        // Deduct balance
        account.setBalance(account.getBalance().subtract(request.getAmount()));
        accountRepository.save(account);

        // Save transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setType(Transaction.TransactionType.DEBIT);
        transaction.setDescription(request.getDescription());
        transaction.setDate(LocalDateTime.now());
        transaction.setAccount(account);
        transactionRepository.save(transaction);

        return "Withdrawn successfully! New Balance: " + account.getBalance();
    }

    // ✅ Transfer Money
    public String transfer(Long fromAccountId, TransactionRequest request) {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found!"));

        Account toAccount = accountRepository
                .findByAccountNumber(request.getToAccountNumber())
                .orElseThrow(() -> new RuntimeException("Destination account not found!"));

        // Check balance
        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance!");
        }

        // Deduct from sender
        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        accountRepository.save(fromAccount);

        // Add to receiver
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));
        accountRepository.save(toAccount);

        // Save DEBIT transaction
        Transaction debit = new Transaction();
        debit.setAmount(request.getAmount());
        debit.setType(Transaction.TransactionType.DEBIT);
        debit.setDescription("Transfer to " + request.getToAccountNumber());
        debit.setDate(LocalDateTime.now());
        debit.setAccount(fromAccount);
        transactionRepository.save(debit);

        // Save CREDIT transaction
        Transaction credit = new Transaction();
        credit.setAmount(request.getAmount());
        credit.setType(Transaction.TransactionType.CREDIT);
        credit.setDescription("Transfer from " + fromAccount.getAccountNumber());
        credit.setDate(LocalDateTime.now());
        credit.setAccount(toAccount);
        transactionRepository.save(credit);

        return "Transfer successful! New Balance: " + fromAccount.getBalance();
    }

    // ✅ Get Transaction History
    public List<Transaction> getTransactionHistory(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}