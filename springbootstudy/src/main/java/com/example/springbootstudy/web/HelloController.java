package com.example.springbootstudy.web;

import com.example.springbootstudy.web.dto.HelloResponseDto;
import com.example.springbootstudy.web.dto.PostsSaveRequestDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloResponseDto(@RequestParam("name") String name,
                                             @RequestParam("mount") int mount){
        return new HelloResponseDto(name, mount);

    }

    @GetMapping("/")
    public String react_Test(String str) {
        System.out.println("before : "+str);
        str = "after"+str;
        return str;
    }

    @PostMapping( value = "/saveTest/")
    public Long savePost(@RequestBody PostsSaveRequestDto requestDto){
        String title,content,author;
        title = requestDto.getTitle();
        content = requestDto.getContent();
        author = requestDto.getAuthor();
        System.out.println(title + " " + author + " " + content);
        return 1l;
    }

}
