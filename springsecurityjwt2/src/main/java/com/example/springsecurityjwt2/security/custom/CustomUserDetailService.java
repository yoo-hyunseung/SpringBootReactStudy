package com.example.springsecurityjwt2.security.custom;

import com.example.springsecurityjwt2.dto.CustomUser;
import com.example.springsecurityjwt2.dto.Users;
import com.example.springsecurityjwt2.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("login -> loadUserByUsername : " + username);

        Users user = userMapper.login(username);

        if (user == null) {
            log.info("사용자 없음 (일치하는 아이디가 없음)");
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다. : " + username);
        }

        log.info("user : ");
        log.info(user.toString());

        // UserDetails -> 시큐리티에서 인증된 사용자를 관리하는 것
        // Users -> UserDetails로 변환해줘야하는데
        // Users -> CustomUser(dto)로 변환 시큐리티에서 사용할 수 있도록
        // customuser 는 UserDetails를 구현한것
        CustomUser customUser = new CustomUser(user);

        log.info("customUser : ");
        log.info(customUser.toString());


        return customUser;
    }
    // 사용자를 인증하는 방식을 셋팅하는 곳

}
