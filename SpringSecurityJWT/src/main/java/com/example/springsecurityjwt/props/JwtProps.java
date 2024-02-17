package com.example.springsecurityjwt.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


// ConfigurationProperties를 사용하여 속성파일로 부터 JWT관련 프로퍼티를 관리하는 클래스
@Data
@Component
@ConfigurationProperties("com.example.springsecurityjwt")
public class JwtProps {
    private String secretKey;
}
