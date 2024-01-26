package com.example.springbootstudy.web;

import com.example.springbootstudy.domain.posts.Posts;
import com.example.springbootstudy.domain.posts.PostsRepository;
import com.example.springbootstudy.web.dto.PostsSaveRequestDto;
import com.example.springbootstudy.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// random 포트 설정
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
//  TestRestTemplate은 REST 방식으로 개발한 API의 Test를 최적화 하기 위해 만들어진 클래스이다.

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();

    }

    @Test
    public void Posts_upload()throws Exception {

        String title = "title";
        String content = "content";
        PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content).author("author")
                .build();

        String url = "http://localhost:" + port + "/api/vi/posts";

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, postsSaveRequestDto, Long.class);
//        ResponseEntity는 사용자의 HttpRequest에 대한 응답하는 데이터를 가진다.  Http Status, Header, Body를 포함한다.
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Test_update()throws Exception {
        // 값을 넣어주고
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // 고칠 대상 id
        Long updateId = savedPosts.getId();
        // 고칠 문장
        String expectedTitle = "title2";
        String expectedContent = "content2";

        // 데이터는 Dto를 통해서 이동 builder 패턴 중요
        PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        HttpEntity<PostsUpdateRequestDto> httpEntity = new HttpEntity<>(postsUpdateRequestDto);
        // HttpEntity -> HTTP 요청 또는 응답 엔터티를 나타냅니다. 일반적으로 헤더와 본문을 포함

        // url
        String url = "http://localhost:" + port + "/api/vi/posts/" + updateId;

        // when
        ResponseEntity<Long> responseEntity =
                testRestTemplate.exchange(url, HttpMethod.PUT, httpEntity, Long.class);
        //exchange 메서드는 다양한 HTTP 메서드 (GET, POST, PUT, DELETE 등)를 사용하여 요청을 보낼 수 있는 일반적인 메서드
        // exchange(보낼 주소, HTTP메서드, HTTP엔티티, 기댓값)

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);


    }

}
