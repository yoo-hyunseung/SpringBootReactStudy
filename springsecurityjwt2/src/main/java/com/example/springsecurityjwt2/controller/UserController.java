package com.example.springsecurityjwt2.controller;


import com.example.springsecurityjwt2.dto.CustomUser;
import com.example.springsecurityjwt2.dto.Users;
import com.example.springsecurityjwt2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * [GET] /users/info - 회원 정보 조회 (ROLE_USER)
 * [POST] /users     - 회원가입      ALL
 * [PUT] /users      - 회원정보 수정 (ROLE_USER)
 * [DELETE] /users   - 회원 탈퇴    (ROLE_ADMIN)확인용
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 사용자 정보 조회
     */
    @Secured("ROLE_USER") // USER 권한 설정
    @GetMapping("/info")
    public ResponseEntity<?> userInfo(@AuthenticationPrincipal CustomUser customUser) {
        /*
            @AuthenticationPrincipal -> 인증된 사용자 정보를 커스텀 유저로 가져올 때 securitycontextholder에서 가져온거

         */
        log.info(":::: customUser ::::");
        log.info("customUser : " + customUser);

        Users user = customUser.getUser();
        log.info("user : " + user);

        // 인증 된 사용자 정보
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        // 인증되지 않음 401
        return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
    }

    /**
     * 회원가입
     *
     * @param user
     * @return
     * @throws Exception 누구나 들어와야하기 때문에 권한 없이 PostMapping("")
     */
    @PostMapping("")
    public ResponseEntity<?> join(@RequestBody Users user) throws Exception {
        log.info("[POST] - /users");
        int result = userService.insert(user);

        if (result > 0) {
            log.info("회원가입 성공! - SUCCESS");
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } else {
            log.info("회원가입 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 회원 정보 수정
     *
     * @param user
     * @return
     * @throws Exception 유저 정보를 가지고 있을때만
     */
    @Secured("ROLE_USER") // USER 권한 설정
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Users user) throws Exception {
        log.info("[PUT] - /users");
        int result = userService.update(user);
        if (result > 0) {
            log.info("회원수정 성공! - SUCCESS");
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } else {
            log.info("회원수정 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * 회원 탈퇴
     *
     * @param userId
     * @return
     * @throws Exception 여기서는 관리자가 할 수 있게 admin권한 임의 설정
     */
    @Secured("ROLE_USER") // admin 권한
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> destroy(@PathVariable("userId") String userId) throws Exception {
        log.info("[DELETE] - /users/{userId}");

        int result = userService.delete(userId);
        if (result > 0) {
            log.info("회원삭제 성공! - SUCCESS");
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } else {
            log.info("회원삭제 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }
}
