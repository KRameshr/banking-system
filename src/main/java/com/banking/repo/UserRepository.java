package com.banking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.model.User;

/**
 * Repository interface for User entity.
 * 
 * Provides database operations for user management.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find user by email address.
	 *
	 * @param email User email
	 * @return Optional user
	 */
	Optional<User> findByEmail(String email);

	/**
	 * Check whether email already exists.
	 *
	 * @param email User email
	 * @return True if email exists, otherwise false
	 */
	boolean existsByEmail(String email);
}