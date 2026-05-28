package com.banking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.model.Transaction;

/**
 * Repository interface for Transaction entity.
 * 
 * Provides database operations for transaction management.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	/**
	 * Find all transactions by account ID.
	 *
	 * @param accountId Account ID
	 * @return List of transactions
	 */
	List<Transaction> findByAccountId(Long accountId);
}