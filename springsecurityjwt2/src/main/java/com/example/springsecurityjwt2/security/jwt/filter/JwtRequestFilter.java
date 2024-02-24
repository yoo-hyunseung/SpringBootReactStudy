package com.example.springsecurityjwt2.security.jwt.filter;

import com.example.springsecurityjwt2.security.jwt.constants.JwtConstants;
import com.example.springsecurityjwt2.security.jwt.provider.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    // OncePerRequestFilter 매 요청마다 필터가 걸리도록 해주는것
    private final JwtTokenProvider jwtTokenProvider;

    // 생성자에서 사용 할수 있도록
    public JwtRequestFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

            //

    /**
     * jwt 요청 필터
     * @param request > Header > Authorization (JWT)
     * JWT 토큰의 유효성을 검사
     *
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //필터가 동작할 때 처리되야 할 로직
        String header = request.getHeader(JwtConstants.TOKEN_HEADER);
        log.info("Authorization : " + header);

        // 토큰을 체크 토큰이 없으면 다음 필터로 이동
        if (header == null || header.length() == 0 || !header.startsWith(JwtConstants.TOKEN_PREFIX)) {
            // 다음 필터로 이동
            filterChain.doFilter(request, response);
            return;
        }

        // Bearer + {jwt} prefix 제거, jwt토큰 해석
        String jwt = header.replace(JwtConstants.TOKEN_PREFIX, "");

        // 토큰 해석
        Authentication authentication = jwtTokenProvider.getAuthentication(jwt);

        if(jwtTokenProvider.validateToken(jwt)){
            // true 토큰 유효
            log.info("유효한 JWT토큰입니다.");
            // 로그인
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 다음 필터
        filterChain.doFilter(request, response);

    }
    // jwt 토큰이 넘어 왔을 때 토큰을 해석해주는 필터



}
