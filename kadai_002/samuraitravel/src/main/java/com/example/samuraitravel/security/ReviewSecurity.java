package com.example.samuraitravel.security;

import static org.springframework.security.config.Customizer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ReviewSecurity {

    @Bean
    public SecurityFilterChain reviewFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/reviews/**") // ★ reviews配下だけに適用されるよう制限
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/reviews/new/**", "/reviews/create/**", "/reviews/edit/**", "/reviews/update/**", "/reviews/delete/**").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(withDefaults())
            .logout(withDefaults());
        return http.build();
    }
}
