package com.example.springbootstudy.domain.posts;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest // 자동으로 h2 데이터 베이스실행
public class PostsRepositoryTest {
    // save(), findAll() 같은 기능을 테스트한다
    @Autowired
    PostsRepository postsRepository;

    @AfterEach // 단위 테스트가 끝날 때 마다 실행
    public void cleanUp(){
        postsRepository.deleteAll();
    }

    // 값을 추가 @테스트

    @Test
    public void boardSaveRead(){
        String title = "test title";
        String content = "test content";

        // save()
        Posts posts = Posts.builder().title(title).content(content).author("hsung122@naver.com")
                .build();
        postsRepository.save(posts);

        // findAll()
        List<Posts> postsList = postsRepository.findAll();

        // 검증
        Posts ps = postsList.get(0);
        assertThat(ps.getTitle()).isEqualTo(title);
        assertThat(ps.getContent()).isEqualTo(content);
        assertThat(ps.getAuthor()).isEqualTo("hsung122@naver.com");

    }
}
