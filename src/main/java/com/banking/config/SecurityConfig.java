package com.banking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.banking.security.JwtFilter;

/**
 * Security configuration class.
 * 
 * Handles: - JWT authentication - Route authorization - Session management -
 * Security filters
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Configure application security rules.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http

				// Enable CORS
				.cors(cors -> {
				})

				// Disable CSRF for REST APIs
				.csrf(csrf -> csrf.disable())

				// Configure request authorization
				.authorizeHttpRequests(auth -> auth

						// Public endpoints
						.requestMatchers("/auth/register", "/auth/login", "/swagger-ui/**", "/swagger-ui.html",
								"/api-docs/**", "/api-docs", "/v3/api-docs/**", "/v3/api-docs")
						.permitAll()

						// Admin-only endpoints
						.requestMatchers("/admin/**").hasRole("ADMIN")

						// All other endpoints require authentication
						.anyRequest().authenticated())

				// Use stateless session management
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Add JWT filter before username/password filter
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	/**
	 * Authentication provider configuration.
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

		provider.setPasswordEncoder(passwordEncoder);

		return provider;
	}

	/**
	 * Authentication manager bean.
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

		return config.getAuthenticationManager();
	}
}