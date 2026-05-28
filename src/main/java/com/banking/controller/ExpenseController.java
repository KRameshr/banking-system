package com.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.dto.ExpenseRequest;
import com.banking.model.Expense;
import com.banking.service.ExpenseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller class for expense management APIs.
 * 
 * Handles: - Adding expenses - Fetching expense history - Viewing budget
 * summary
 */
@RestController
@RequestMapping("/expense")
@Tag(name = "Expense", description = "Expense management APIs")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	/**
	 * Add a new expense for an account.
	 *
	 * @param accountId Account ID
	 * @param request   Expense request data
	 * @return Success message
	 */
	@Operation(summary = "Add expense")
	@PostMapping("/add/{accountId}")
	public ResponseEntity<String> addExpense(@PathVariable Long accountId, @Valid @RequestBody ExpenseRequest request) {

		String response = expenseService.addExpense(accountId, request);

		return ResponseEntity.ok(response);
	}

	/**
	 * Fetch all expenses for an account.
	 *
	 * @param accountId Account ID
	 * @return List of expenses
	 */
	@Operation(summary = "Get all expenses")
	@GetMapping("/list/{accountId}")
	public ResponseEntity<List<Expense>> getExpenses(@PathVariable Long accountId) {

		List<Expense> expenses = expenseService.getExpenses(accountId);

		return ResponseEntity.ok(expenses);
	}

	/**
	 * Fetch budget summary for an account.
	 *
	 * @param accountId Account ID
	 * @return Budget summary details
	 */
	@Operation(summary = "Get budget summary")
	@GetMapping("/summary/{accountId}")
	public ResponseEntity<Map<String, Object>> getSummary(@PathVariable Long accountId) {

		Map<String, Object> summary = expenseService.getBudgetSummary(accountId);

		return ResponseEntity.ok(summary);
	}
}