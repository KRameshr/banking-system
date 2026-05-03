package com.banking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.banking.repo.UserRepository;

@Configuration
public class JwtConfig {

    @Autowired
    private UserRepository userRepository;

    // ✅ Load User from Database
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            com.banking.model.User user = userRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException(
                            "User not found with email: " + email));

            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        };
    }

    // ✅ Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}