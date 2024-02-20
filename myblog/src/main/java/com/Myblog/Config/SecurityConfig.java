package com.Myblog.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .requestMatchers("/api/post").authenticated()
                    .requestMatchers( "/api/post/1**").permitAll()
                    .anyRequest().authenticated();
                   http .formLogin(withDefaults());
                   http .httpBasic(withDefaults());
            return http.build();
        }
    }