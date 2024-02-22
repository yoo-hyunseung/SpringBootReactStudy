package com.example.springsecurityjwt2.security.jwt.provider;

import com.example.springsecurityjwt2.dto.CustomUser;
import com.example.springsecurityjwt2.dto.UserAuth;
import com.example.springsecurityjwt2.dto.Users;
import com.example.springsecurityjwt2.mapper.UserMapper;
import com.example.springsecurityjwt2.prop.JwtProps;
import com.example.springsecurityjwt2.security.jwt.constants.JwtConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * provider 역할 -> 실제 토큰 관련 기능을 제공해주는 클래스
 * 1. 토큰 생성
 * 2. 토큰 해석
 * 3. 토큰 유효성 검사
 */
@Slf4j
@Component
public class JwtTokenProvider {
    @Autowired
    private JwtProps jwtProps;

    @Autowired
    private UserMapper userMapper;
    // 토큰생성
    public String createToken(int userNo, String userId, List<String> roles){
        // jwt token 생성 라이브러리
        String jwt = Jwts.builder()
                .signWith(getShaKey(), Jwts.SIG.HS512) // 서명에 필요한 키와 알고리즘
                .header()
                .add("typ", JwtConstants.TOKEN_TYPE) // 해더 설정 (JWT)
                .and()
                .expiration(new Date(System.currentTimeMillis() + 864000000)) // 토큰 만료시간(10일)
                .claim("uno", "" + userNo) // 사용자 번호 // Payload에 넣어 주는 데이터
                .claim("uid", userId) // 사용자 아이디
                .claim("rol", roles) // 클래임 설정 권한
                .compact();

        log.info("jwt : " + jwt);
        return jwt;
    }


    /**
     * 토큰 해석
     * Authorization : Bearer + {JWT} (authHeader)
     * -> JWT 추출
     *  -> UsernamePasswordAuthenticationToken
     *
     */
    public UsernamePasswordAuthenticationToken getAuthentication(String authHeader){
        // String authHeader => Bearer {jwt} 형태로 들어 온다
        if(authHeader == null || authHeader.length()==0){
            return null;
        }
        try {
            // jwt 추출 (Bearer + {jwt}) -> {jwt} 만 남긴다.
            String jwt = authHeader.replace(JwtConstants.TOKEN_PREFIX, "");
            //JwtConstants.TOKEN_PREFIX -> "Bearer "

            // jwt 파싱 암호회된 jwt 를 문자열로
            Jws<Claims> parsedToken = Jwts.parser()
                    .verifyWith(getShaKey())
                    .build()
                    .parseSignedClaims(jwt);

            // 파싱을 통해 암호를 문자열로 변경
            log.info("parsedToken : " + parsedToken);

            // 인증된 사용자
            // 번호 getPayload로 꺼낸다.
            String userNo = parsedToken.getPayload().get("uno").toString();
            int no = (userNo == null ? 0 : Integer.parseInt(userNo));
            log.info("userNo : " + userNo);

            // 인증된 사용자 아이디
            String userId = parsedToken.getPayload().get("uid").toString();
            log.info("userId : " + userId);

            // 인증된 사용자 권한
            Claims claims = parsedToken.getPayload();
            Object roles = claims.get("rol");
            log.info("roles : " + roles);

            // 토큰에 userId가 있는지 확인
            if (userId == null || userId.length() == 0) {
                return null;
            }

            // 아이디가 존재할 때 객체에 셋팅
            Users user = new Users();
            user.setNo(no);
            user.setUserId(userId);
            // 권한도 셋팅
            List<UserAuth> authList = ((List<?>) roles)
                    .stream()
                    .map(auth -> new UserAuth(userId, auth.toString()))
                    .collect(Collectors.toList());
            user.setAuthList(authList);


            // 시큐리티에서 사용할 수 있게 커스텀 유저를 simplegrantedAuthority로 변환
            // CustomUser에 권한 담기
            List<SimpleGrantedAuthority> authorities = ((List<?>) roles)
                    .stream()
                    .map(auth -> new SimpleGrantedAuthority((String) auth))
                    .collect(Collectors.toList());

            // 토큰이 유효하면
            // name, email 도 담기
            try {
                Users userInfo = userMapper.select(no);
                if (userInfo != null) {
                    // 필요한 추가정보를 객체에 담는다.
                    user.setEmail(userInfo.getEmail());
                    user.setName(userInfo.getName());
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                log.error("토큰 유효 =? DB추가 정보 조회시 에러 발생....");
            }
            // UserDetails 로 업캐스팅 (시큐리티에서 사용할 수 있는 방식)
            UserDetails userDetails = new CustomUser(user);

            // 토큰에 유저 정보와 인증 정보를 받아서 리턴
            // 이 정보를 필터에서 받아서 인증처리 할 것 new UsernamePasswordAuthenticationToken(사용자 정보 객체, 비밀번호, 사용자의 권한(목록))
            return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

        } catch (ExpiredJwtException exception) {
            log.warn("request to parse expired JWT : {} failed : {}", authHeader, exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.warn("request to parse unsupported JWT {} failed {}", authHeader, exception.getMessage());
        } catch (MalformedJwtException exception) {
            log.warn("request to parse invalid JWT {} failed {}", authHeader, exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.warn("request to parse empty or null JWT {} failed {}", authHeader, exception.getMessage());
        }
        return null;
    }

    /** 토큰 유효성 검사 메소드
     * 유효성 검사 -> 기간이 남았는지?
     * @param jwt
     * @return
     *  true  : 유효
     *  false : 만료
     *
     */
    public boolean validateToken(String jwt){
        try {
            // 파싱을 다시 해봐야한다.
            // 파싱하는 작업에서 비밀키, 시그니처 위변조 가능성
            Jws<Claims> parsedToken = Jwts.parser()
                    .verifyWith(getShaKey())
                    .build()
                    .parseSignedClaims(jwt);

            // 만료기간을 찍어본다.
            log.info("####### 토큰 만료기간 #######");
            log.info("->" + parsedToken.getPayload().getExpiration());
            /**
             PAYLOAD
             {
             "exp": 1703140095,       <- 이 날짜에 만료다
             "uid": "joeun",
             "rol": [
             "ROLE_USER"
             ]
             }
             Date type
             */
            Date exp = parsedToken.getPayload().getExpiration();
            // 만료시간, 현재시간 비교
            // 2024.12.01 - 2024.12.14
            return !exp.before(new Date()); // 오늘 날짜로
            // 10일 지났으면 true, 아니면 false !을 붙여서 지나면 false 반환
        } catch (ExpiredJwtException e) {
            log.error("Token Expired"); // 토큰 만료
            return false;
        } catch (JwtException e){
            log.error("Token Tampered"); // 토큰 손실 토큰 내용이 변조, 시크릿키, 유저 정보등...
            return false;
        } catch (NullPointerException e){
            log.error("Token is null"); // 토큰 없음
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    // secretKey -> signingKey()
    private byte[] getSigningKey(){
        return jwtProps.getSecretKey().getBytes();
    }

    // secretKey -> (HMAC-SHA algorithms) -> signingKey
    private SecretKey getShaKey(){
        return Keys.hmacShaKeyFor(getSigningKey());
    }
}
