package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.model.Account;
import com.banking.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller class for handling account-related APIs.
 */
@RestController
@RequestMapping("/account")
@Tag(name = "Account", description = "Account management APIs")
public class AccountController {

	@Autowired
	private AccountService accountService;

	/**
	 * Fetch account details using user ID.
	 *
	 * @param userId User ID
	 * @return Account details
	 */
	@Operation(summary = "Get account by user ID")
	@GetMapping("/user/{userId}")
	public ResponseEntity<Account> getAccountByUserId(@PathVariable Long userId) {

		Account account = accountService.getAccountByUserId(userId);

		return ResponseEntity.ok(account);
	}

	/**
	 * Fetch account details using account number.
	 *
	 * @param accountNumber Account number
	 * @return Account details
	 */
	@Operation(summary = "Get account by account number")
	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getAccountByNumber(@PathVariable String accountNumber) {

		Account account = accountService.getAccountByNumber(accountNumber);

		return ResponseEntity.ok(account);
	}
}