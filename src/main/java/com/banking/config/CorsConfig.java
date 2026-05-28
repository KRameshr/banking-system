package com.banking.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration class for handling Cross-Origin Resource Sharing (CORS).
 * 
 * This allows the frontend application to communicate with the backend from
 * different origins.
 */
@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {

		// Create CORS configuration
		CorsConfiguration configuration = new CorsConfiguration();

		// Allow requests from all origins
		configuration.setAllowedOriginPatterns(List.of("*"));

		// Allow common HTTP methods
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

		// Allow all headers
		configuration.setAllowedHeaders(List.of("*"));

		// Allow credentials such as cookies or authorization headers
		configuration.setAllowCredentials(true);

		// Register configuration for all API endpoints
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", configuration);

		return new CorsFilter(source);
	}
}