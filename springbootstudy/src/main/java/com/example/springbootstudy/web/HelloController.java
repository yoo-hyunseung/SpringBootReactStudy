package com.example.springbootstudy.web;

import com.example.springbootstudy.web.dto.HelloResponseDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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


}
