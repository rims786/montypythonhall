package com.example.treasuresim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the application.
 * Provides basic security settings and configurations for HTTP security.
 * This configuration is suitable for development environment with open access.
 *
 * @Configuration indicates that this class contains Spring configuration
 * @EnableWebSecurity enables Spring Security's web security support
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for the application.
     * Current configuration:
     * - CSRF protection is disabled
     * - All requests are permitted without authentication
     *
     * @param http The HttpSecurity object to be configured
     * @return A configured SecurityFilterChain
     * @throws Exception if an error occurs during security configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection - Note: Enable this in production
                .csrf(csrf -> csrf.disable())

                // Configure authorization for HTTP requests
                .authorizeHttpRequests(auth -> auth
                        // Allow all requests without authentication
                        // For production: Configure specific access rules
                        .anyRequest().permitAll()
                );

        // Build and return the configured security filter chain
        return http.build();
    }
}
