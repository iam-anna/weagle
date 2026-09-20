package com.weagle.config;

import com.weagle.security.CustomerUserDetailsService;
import com.weagle.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationProvider authenticationProvider(
            CustomerUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider,
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()

                        // Strategies
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/strategies",
                                "/api/strategies/**"
                        ).authenticated()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/strategies"
                        ).hasRole("LEADER")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/strategies/**"
                        ).hasRole("LEADER")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/strategies/**"
                        ).hasRole("LEADER")

                        // Ideas
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/ideas",
                                "/api/ideas/**"
                        ).authenticated()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/ideas"
                        ).hasRole("OPERATOR")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/ideas/**"
                        ).hasRole("OPERATOR")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/ideas/**"
                        ).hasRole("OPERATOR")

                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/api/ideas/*/approve",
                                "/api/ideas/*/priority"
                        ).hasRole("MANAGER")

                        // Projects
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/projects",
                                "/api/projects/**"
                        ).authenticated()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/projects"
                        ).hasRole("MANAGER")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/projects/**"
                        ).hasRole("MANAGER")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/projects/**"
                        ).hasRole("MANAGER")

                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/api/projects/*/progress",
                                "/api/projects/*/results"
                        ).hasRole("LEADER")

                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
