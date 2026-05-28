package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.dto.LoginRequest;
import com.banking.dto.RegisterRequest;
import com.banking.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller class for authentication APIs.
 * 
 * Handles: - User registration - User login - JWT token generation
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Register and Login APIs")
public class AuthController {

	@Autowired
	private AuthService authService;

	/**
	 * Register a new user.
	 *
	 * @param request Register request data
	 * @return Success message
	 */
	@Operation(summary = "Register new user")
	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {

		String response = authService.register(request);

		return ResponseEntity.ok(response);
	}

	/**
	 * Authenticate user and generate JWT token.
	 *
	 * @param request Login credentials
	 * @return JWT token
	 */
	@Operation(summary = "Login and get JWT token")
	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {

		String token = authService.login(request);

		return ResponseEntity.ok(token);
	}
}