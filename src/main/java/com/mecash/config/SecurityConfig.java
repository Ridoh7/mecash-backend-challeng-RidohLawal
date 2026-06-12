package com.mecash.config;

import com.mecash.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration.
 *
 * Configures authentication, password encryption,
 * and access control for application endpoints.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    /**
     * Configures BCrypt password encryption.
     *
     * All user passwords are stored in
     * encrypted form before persistence.
     *
     * @return password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures endpoint security rules.
     *
     * Public endpoints:
     * - User registration
     * - User login
     * - Swagger documentation
     *
     * All other endpoints require authentication.
     *
     * @param http HttpSecurity configuration
     * @return security filter chain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for REST API usage
                .csrf(AbstractHttpConfigurer::disable)

                // Configure endpoint authorization rules
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/api/auth/signup",
                                "/api/auth/login",
                                "/swagger-ui/**",
                                "/v3/api-docs/**")
                        .permitAll()

                        .anyRequest()
                        .authenticated())

                // Enable HTTP Basic Authentication
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Configures authentication using the application's UserDetailsService.
     *
     * Spring Security uses this provider to retrieve users from the database and
     * validate credentials.
     *
     * @return authentication provider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
}