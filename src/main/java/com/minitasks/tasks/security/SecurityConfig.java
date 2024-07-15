package com.minitasks.tasks.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("removal")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/tasks**").permitAll()
                .requestMatchers("/api/auth**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return httpSecurity.build();
    }
}
