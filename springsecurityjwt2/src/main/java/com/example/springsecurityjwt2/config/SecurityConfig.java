package com.example.springsecurityjwt2.config;

import com.example.springsecurityjwt2.security.custom.CustomUserDetailService;
import com.example.springsecurityjwt2.security.jwt.filter.JwtAuthenticationFilter;
import com.example.springsecurityjwt2.security.jwt.filter.JwtRequestFilter;
import com.example.springsecurityjwt2.security.jwt.provider.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;


@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private AuthenticationManager authenticationManager;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        log.info(" 시큐리티 설정...");

        // 폼 기반 로그인 비활성화
        http.formLogin(login -> login.disable());

        // HTTP 기본 인증 비활성화
        http.httpBasic(basic -> basic.disable());

        // CSRF(Cross-Site Request Forgery)  공격 방어 기능 비활성화
        http.csrf(csrf -> csrf.disable());

        /* 필터 설정
        // jwt request filter 1번 순서에 먼저 걸린다
        // -> jwt 토큰을 해석

        // jwt filter     2번 순서(로그인)
        // ->
        // username , password -> jwt 토큰을 생성
         */
        // addFilterAt(설정해줄 필터 인스턴스, 어떤 필터에 대해서 동작을 시킬지 클래스)
        http.addFilterAt(new JwtAuthenticationFilter(authenticationManager,jwtTokenProvider)
                        , UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtRequestFilter(jwtTokenProvider)
                        , OncePerRequestFilter.class);

        // 인가 설정
        /*
        정적자원 사용 - ALL
        로그인 경로 /login - ALL
        /user/**   -> USER , ADMIN
        /admin/**  -> ADMIN
         */
        http.authorizeHttpRequests(authorizeRequest ->

                authorizeRequest
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //static 정적 자원 경로 허용
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("user/**").hasAnyRole("USER", "ADMIN") // 2개이상 애니롤
                        .requestMatchers("/admin/**").hasRole("ADMIN") // 1개만
                        .anyRequest().authenticated() // 나머지는 인증된 사용자만
                // 상위경로를 먼저 적어주고 하위 경로를 적어준다.
        );

        /* 인증 방식 설정

            인 메모리 방식
            JDBC 방식
            커스텀 방식 (userDetailService) -> 우리가 구현해서 넣어 준다
         */
        http.userDetailsService(customUserDetailService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 암호화 알고리즘 방식
    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;
    }
}
