package com.example.springbootstudy.service;

import com.example.springbootstudy.domain.posts.Posts;
import com.example.springbootstudy.domain.posts.PostsRepository;
import com.example.springbootstudy.web.dto.PostsResponseDto;
import com.example.springbootstudy.web.dto.PostsSaveRequestDto;
import com.example.springbootstudy.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    // 등록
    @Transactional
    public Long save(PostsSaveRequestDto postsSaveRequestDto) {
        return postsRepository.save(postsSaveRequestDto.toEntity()).getId();
    }

    //수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto) {
        Posts post = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("no posts id=" + id)
        );
        //update 는 쿼리문이 없는데 이는 JPA의 영속성 컨텍스트 때문이다.
        // 영속성 컨텍스트는 엔티티를 영구 저장하는 환경
        // JPA의 핵심내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 마냐
        // JPA의 엔티티 매니저가 활성화된 상태면 트랜잭션 안에서 데이터베이스에서 가져온 값을 유지
        // 가져온 값을 변경하고 트랜잭션이 끝나면 자동으로 변경분을 데이터 베이스에 반영
        // 쿼리를 날릴 필요가 없다 -> 더티체킹
        post.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent());
        return id;
    }

    // 조회
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("no posts response id =" + id));
        return new PostsResponseDto(entity);
    }

    public List<PostsResponseDto> findAll(){
        List<Posts> postsList = postsRepository.findAll();
        List<PostsResponseDto> postsResponseDtos = new ArrayList<>();
        for(int i =0 ; i< postsList.size();i++){
            postsResponseDtos.add(new PostsResponseDto(postsList.get(i)));
        }
//        Posts postsAll = postsRepository.findAll();
        return postsResponseDtos;
    }
}
