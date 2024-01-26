package com.example.springbootstudy.web.dto;

import com.example.springbootstudy.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    // 글 조회하는거
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
