# oauth2 연동

1. build.gradle 에 스프링 시큐리티 의존성 추가
2. application-oauth.properties에 공급자 정보 추가
~~~ properties
# 구글이나 페이스북은 안적어도 되는데, 네이버나 카카오는 적어줘야함(기본 제공 provider가 아니기 때문에)
spring.security.oauth2.client.registration.naver.client-id = [클라이언트 id]
spring.security.oauth2.client.registration.naver.client-secret= [클라이언트 pw]
spring.security.oauth2.client.registration.naver.client-name=Naver
spring.security.oauth2.client.registration.naver.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.naver.redirect-uri=http://localhost:8080/login/oauth2/code/naver

# Naver Provider 등록!
spring.security.oauth2.client.provider.naver.authorization-uri=https://nid.naver.com/oauth2.0/authorize
spring.security.oauth2.client.provider.naver.token-uri=https://nid.naver.com/oauth2.0/token
spring.security.oauth2.client.provider.naver.user-info-uri=https://openapi.naver.com/v1/nid/me
spring.security.oauth2.client.provider.naver.user-name-attribute=response # 네이버가 회원정보를 json으로 넘겨주는데, response라는 키값으로 리턴해준다.

출처: https://iseunghan.tistory.com/300 [iseunghan:티스토리]
~~~

