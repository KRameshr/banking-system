package com.banking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.model.Account;

/**
 * Repository interface for Account entity.
 * 
 * Provides database operations for accounts.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	/**
	 * Find account by account number.
	 *
	 * @param accountNumber Account number
	 * @return Optional account
	 */
	Optional<Account> findByAccountNumber(String accountNumber);

	/**
	 * Find account by user ID.
	 *
	 * @param userId User ID
	 * @return Optional account
	 */
	Optional<Account> findByUserId(Long userId);
}