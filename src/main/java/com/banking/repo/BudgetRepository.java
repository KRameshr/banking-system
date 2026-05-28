package com.banking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.model.Budget;

/**
 * Repository interface for Budget entity.
 * 
 * Provides database operations for budget management.
 */
@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

	/**
	 * Find budget details using account ID.
	 *
	 * @param accountId Account ID
	 * @return Optional budget details
	 */
	Optional<Budget> findByAccountId(Long accountId);
}