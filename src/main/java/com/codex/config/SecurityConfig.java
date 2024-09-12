package com.codex.config;

import com.codex.security.webtoken.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
<<<<<<< HEAD
=======
import org.springframework.security.config.Customizer;
>>>>>>> oauth2.0
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
<<<<<<< HEAD
                        .requestMatchers("/api/v1/register/user", "/api/v1/authenticate").permitAll()
                        .requestMatchers("/api/v1/users", "/api/v1/categories", "/api/v1/expenses").hasRole("USER")
                        .requestMatchers("/api/v1/users/**", "/api/v1/categories/**", "/api/v1/expenses/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
=======
                        .requestMatchers("/api/v1/register/user", "/api/v1/authenticate", "/api/v1/auth").permitAll()
//                        .requestMatchers("/api/v1/users", "/api/v1/categories", "/api/v1/expenses", "/profile")
//                        .requestMatchers("/api/v1/users/**", "/api/v1/categories/**", "/api/v1/expenses/**")
                        .anyRequest().authenticated())
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(Customizer.withDefaults())
>>>>>>> oauth2.0
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll);

        return http.build();
    }
<<<<<<< HEAD
=======

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/api/v1/register/user", "/api/v1/authenticate", "/api/v1/auth").permitAll()
//                        .requestMatchers("/api/v1/users", "/api/v1/categories", "/api/v1/expenses", "/profile").hasRole("OIDC_USER")
//                        .requestMatchers("/api/v1/users/**", "/api/v1/categories/**", "/api/v1/expenses/**").hasRole("ADMIN")
//                        .anyRequest().authenticated())
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .oauth2Login(Customizer.withDefaults())
//                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
//
//        return http.build();
//    }
>>>>>>> oauth2.0
}
