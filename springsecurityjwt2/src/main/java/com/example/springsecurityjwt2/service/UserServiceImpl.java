package com.example.springsecurityjwt2.service;

import com.example.springsecurityjwt2.dto.UserAuth;
import com.example.springsecurityjwt2.dto.Users;
import com.example.springsecurityjwt2.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;
    /*
    회원 등록 (가입)
    1. 비밀번호 암호회
    2. 회원 등록
    3. 권한 등록
    * */
    @Override
    public int insert(Users user) throws Exception {
        // 비밀번호 암호회
        String userPw = user.getUserPw();
        String encodePw = passwordEncoder.encode(userPw);
        user.setUserPw(encodePw);

        // 회원등록
        int result = userMapper.insert(user);

        // 권한등록
        if(result > 0){
            UserAuth userAuth = new UserAuth();
            userAuth.setUserId(user.getUserId());
            userAuth.setAuth("ROLE_USER"); // 기본 권한(ROLE_USER)
            result = userMapper.insertAuth(userAuth);
        }
        return result;
    }

    /**
        회원 조회
     */
    @Override
    public Users select(int userNo) throws Exception {
        return null;
    }
    /**
        로그인
     */
    @Override
    public void login(Users users, HttpServletRequest request) throws Exception {
        String username = users.getName();
        String password = users.getUserPw();
        log.info("username : "+username);
        log.info("password : " + password);

        // AuthenticationManager 인증을 관리하는 객체 아이디와 패스워드를 통해 검증
        // 아이디 비밀번호 인증 토큰 생성
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(username, password);

        // 토큰에 요청정보 등록
        token.setDetails(new WebAuthenticationDetails(request));

        // 토큰을 이용해서 인증 요청 로그인
        Authentication authentication = authenticationManager.authenticate(token);

        log.info("인증여부 : " + authentication.isAuthenticated());

        User authUser = (User) authentication.getPrincipal();
        log.info("인증된 사용자 정보 : "+authUser.getUsername());

        // 시큐리티 컨텍스트에 인증 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    /**
     회원정보를 수정

     */
    @Override
    public int update(Users user) throws Exception {
        // 비밀번호 암호회
        String userPw = user.getUserPw();
        String encodedPw = passwordEncoder.encode(userPw);
        user.setUserPw(encodedPw);

        int result = userMapper.update(user);

        return result;
    }

    /**
     *
     * 회원삭제( 회원 탈퇴 )
     */
    @Override
    public int delete(String userId) throws Exception {
        return userMapper.delete(userId);
    }
}

