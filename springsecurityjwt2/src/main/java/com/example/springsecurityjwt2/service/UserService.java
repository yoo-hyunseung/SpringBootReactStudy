package com.example.springsecurityjwt2.service;

import com.example.springsecurityjwt2.dto.Users;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

public interface UserService {
    //회원등록
    public int insert(Users user) throws Exception;

    // 회원조회
    public Users select(int userNo) throws Exception;

    // 로그인
    public void login(Users users, HttpServletRequest request) throws Exception;

    // 회원수정
    public int update(Users user) throws Exception;

    // 회원 삭제
    public int delete(String userId) throws Exception;
}
