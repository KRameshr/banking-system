package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.dto.BudgetRequest;
import com.banking.model.Budget;
import com.banking.service.BudgetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/budget")
@Tag(name = "Budget", description = "Budget management APIs")
public class BudgetController {

	@Autowired
	private BudgetService budgetService;

	@Operation(summary = "Set budget limits")
	@PostMapping("/set/{accountId}")
	public ResponseEntity<String> setBudget(@PathVariable Long accountId, @RequestBody BudgetRequest request) {

		String response = budgetService.setBudget(accountId, request);

		return ResponseEntity.ok(response);
	}


	@Operation(summary = "Get budget limits")
	@GetMapping("/get/{accountId}")
	public ResponseEntity<Budget> getBudget(@PathVariable Long accountId) {

		Budget budget = budgetService.getBudget(accountId);

		return ResponseEntity.ok(budget);
	}
}