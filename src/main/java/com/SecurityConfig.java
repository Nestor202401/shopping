package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // 禁用 CSRF 保護
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/test/Order.html").authenticated()  // 確保需要 token 的路徑需要身份驗證
                    .antMatchers("/orders/**").authenticated() // 確保 /orders/** 也需要身份驗證
                    .antMatchers("/users/login", "/users/register").permitAll()  // 允許訪問登入和註冊
                    .anyRequest().permitAll() // 允許其他所有請求
            )
            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
