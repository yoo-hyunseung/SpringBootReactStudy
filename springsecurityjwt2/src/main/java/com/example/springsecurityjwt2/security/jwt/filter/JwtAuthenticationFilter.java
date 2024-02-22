package com.example.springsecurityjwt2.security.jwt.filter;

import com.example.springsecurityjwt2.dto.CustomUser;
import com.example.springsecurityjwt2.security.jwt.constants.JwtConstants;
import com.example.springsecurityjwt2.security.jwt.provider.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  client -> filter -> server ->
 *  filter -> /login 경로
 *  username, password 인증 시도 attemptAuthentication
 *  인증 실패시 -> response > status > 401 unauthorized
 *  인증 성공시 successfulAuthentication 실행
 *  -> 성공시 JWT 토큰 생성
 *     생성된 토큰을 response > Headers > authorization 담아서 보낸다
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // jwt token을 생성하는 필터
    // extends UsernamePasswordAuthenticationFilter 시큐리티와 연결
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    //
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        // 필터의 URL 경로를 설정 : /login
        setFilterProcessesUrl("/login");
    }
    /**
     * 인증을 시도 하는 메소드
     * 로그인 경로(/login)로 접근시 필터링
     * : /login 경로로 요청하면, 필터로 걸러서 인증을 시도
     *
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 시도, 접근 -> 인증을 시도하는 필터 메소드
        // request , response 에서 값을 가져온다.
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        log.info("username : " + username);
        log.info("password : " + password);

        // 사용자 인증정보 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        // 사용자 인증(로그인)
        //@Autowired
        //    private AuthenticationManager authenticationManager; 사용불가
        // 생성자를 만들어서 주입해줘야한다.

        authentication = authenticationManager.authenticate(authentication); // 객체를 가져와야 한다.
        // UserDetailsService -> DB에서 가져온 값을 -> PasswordEncoder 실행 (authenticate(authentication))
        log.info("인증 여부 : " + authentication.isAuthenticated());

        // 인증 실패시 (username,password) 불일치
        if (!authentication.isAuthenticated()) {
            log.info("인증실패 : 아이디 또는 비밀번호가 일치하지 않습니다.");
            response.setStatus(401); // 401 인증 실패
        }

        return authentication;
    }

    /**
     * 인증 성공 메소드
     * jwt token을 생성하고
     * jwt를 응답 해더에 담아주는 역할
     *
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        // 인증이 성공시 호출되는 메소드
        // jwt provider 에서 생성, 검증 이후 여기서 실행
        //Authentication authentication 인증된 정보 확인 매개변수
        log.info("인증 성공 ...");

        // getPrincipal 인증정보를 가져올 수 있다.
        CustomUser user = (CustomUser) authentication.getPrincipal();
        int userNo = user.getUser().getNo();
        String userId = user.getUser().getUserId();

        List<String> roles = user.getUser().getAuthList()
                .stream()
                .map((auth)->auth.getAuth())
                .collect(Collectors.toList());

        // JwtProvider 로
        String jwt = jwtTokenProvider.createToken(userNo, userId, roles);

        //  Header : {Authorization : Bearer + {jwt} }
        response.addHeader(JwtConstants.TOKEN_HEADER,JwtConstants.TOKEN_PREFIX+jwt);
        response.setStatus(200);

    }

}
