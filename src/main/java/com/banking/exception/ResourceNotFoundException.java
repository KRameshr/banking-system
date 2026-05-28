package com.banking.exception;

/**
 * Custom exception class for handling resource not found scenarios.
 */
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * Constructor for ResourceNotFoundException.
	 *
	 * @param message Exception message
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}
}