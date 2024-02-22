package com.example.springsecurityjwt2.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("com.example.springsecurityjwt2")
public class JwtProps {
    private String secretKey;
}
