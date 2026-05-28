package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.dto.BudgetRequest;
import com.banking.model.Budget;
import com.banking.service.BudgetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller class for budget management APIs.
 * 
 * Handles: - Setting budget limits - Fetching budget details
 */
@RestController
@RequestMapping("/budget")
@Tag(name = "Budget", description = "Budget management APIs")
public class BudgetController {

	@Autowired
	private BudgetService budgetService;

	/**
	 * Set budget limits for an account.
	 *
	 * @param accountId Account ID
	 * @param request   Budget request data
	 * @return Success message
	 */
	@Operation(summary = "Set budget limits")
	@PostMapping("/set/{accountId}")
	public ResponseEntity<String> setBudget(@PathVariable Long accountId, @RequestBody BudgetRequest request) {

		String response = budgetService.setBudget(accountId, request);

		return ResponseEntity.ok(response);
	}

	/**
	 * Fetch budget details for an account.
	 *
	 * @param accountId Account ID
	 * @return Budget details
	 */
	@Operation(summary = "Get budget limits")
	@GetMapping("/get/{accountId}")
	public ResponseEntity<Budget> getBudget(@PathVariable Long accountId) {

		Budget budget = budgetService.getBudget(accountId);

		return ResponseEntity.ok(budget);
	}
}