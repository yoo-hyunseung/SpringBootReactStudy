package com.example.springbootstudy.web;

import com.example.springbootstudy.service.PostsService;
import com.example.springbootstudy.web.dto.PostsResponseDto;
import com.example.springbootstudy.web.dto.PostsSaveRequestDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequiredArgsConstructor는 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
// @Autowired 대신 사용
@RequiredArgsConstructor
@RestController
@CrossOrigin("http://localhost:3000")
// CORS 설정
public class ReactPostApiRestController {
    //RequiredArgsConstructor로 final 필드 의존성 주입
    // final 불변성
    private final PostsService postsService;

    @PostMapping(value = "/save/")
    public Long postSave(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @GetMapping(value ="/select")
    public List<PostsResponseDto> postList(){
        return postsService.findAll();
    }

    @GetMapping(value = "/selectOne")
    public PostsResponseDto postFindOne(@RequestParam Long id){
        return postsService.findById(id);
    }
}
