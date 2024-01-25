package com.example.springbootstudy.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest( = HelloController.class)
@WebMvcTest(controllers = HelloController.class) // SpringBootTest중에 하나만 경우에 맞게 설정해야한다.

public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_return()throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // mockmvc 를 통해 http get요청
                .andExpect(status().isOk()) // http staus ok -> 200
                .andExpect(content().string(hello)); // controller에서 hello를 리턴하기 때문에 리턴값 검증

    }

    @Test
    public void hello_dto_return()throws Exception {
        String name ="hello";
        int mount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("mount", String.valueOf(mount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.mount", is(mount)));
    }

}
