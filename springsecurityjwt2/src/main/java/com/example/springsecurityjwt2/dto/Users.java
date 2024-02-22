package com.example.springsecurityjwt2.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Users {
    private int no;
    private String userId;
    private String userPw;
    private String userPwCheck; // 비밀번호 체크
    private String name;
    private String email;
    private Date regDate;
    private Date updDate;
    private int enabled; // 활성화 여부

    // 권한 목록
    List<UserAuth> authList;

    public Users(){

    }

    public Users(Users user) {
        this.no = user.getNo();
        this.userId = user.getUserId();
        this.userPw = user.getUserPw();
        this.email = user.getEmail();
        this.authList = user.getAuthList();
    }
}
