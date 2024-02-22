package com.example.springsecurityjwt2.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
public class CustomUser implements UserDetails {
    // Users -> 스프링 시큐리티 형식에 맞는 형태로 변환하기위해서
    // Users -> CustomUser -> UserDetails
    // CustomUser가 UserDetails 를 구현 해줘야 한다

    private Users user;

    // 생성자
    public CustomUser(Users user){
        this.user = user;
    }

    /**
     * 권한 getter 메소드
     * 권한 관리 -> authList
     * List<UserAuth> -> 스프링 시큐리티 -> Collection<SimpleGrantedAuthority> (auth) 권한정보만 필요해서 그것만 뽑아서 보냄
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한처리
        List<UserAuth> authList = user.getAuthList(); // UserAuth (authNo, userId, auth)

        // SimpleGrantedAuthority() => "ROLE_USER, ROLE_ADMIN ..만 필요
        Collection<SimpleGrantedAuthority> roleList = authList.stream()
                .map((auth)-> new SimpleGrantedAuthority(auth.getAuth()))
                .collect(Collectors.toList());
        return roleList;
    }

    @Override
    public String getPassword() {
        return user.getUserPw();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 영구정지, 휴면계정
        return user.getEnabled() == 0 ? false : true;
    }

}
