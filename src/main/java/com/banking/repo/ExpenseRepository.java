package com.banking.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.model.Expense;

/**
 * Repository interface for Expense entity.
 * 
 * Provides database operations for expense management.
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	/**
	 * Find all expenses by account ID.
	 *
	 * @param accountId Account ID
	 * @return List of expenses
	 */
	List<Expense> findByAccountId(Long accountId);

	/**
	 * Find expenses between given dates for a specific account.
	 *
	 * @param accountId Account ID
	 * @param start     Start date and time
	 * @param end       End date and time
	 * @return List of filtered expenses
	 */
	List<Expense> findByAccountIdAndDateBetween(Long accountId, LocalDateTime start, LocalDateTime end);
}