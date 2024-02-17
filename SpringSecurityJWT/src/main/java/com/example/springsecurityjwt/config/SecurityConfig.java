package com.example.springsecurityjwt.config;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        // 폼기반 로그인 비활성화
        http.formLogin(login -> login.disable());

        //  HTTP기본 인증 비활성화
        http.httpBasic(basic -> basic.disable());

        // CSRF(cross site request forgery)비활성
        http.csrf(csrf -> csrf.disable());

        // 세션 비활성화 세션말고 JWT를 통해 인증 세션 필요 없음
        http.sessionManagement(management-> management
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
