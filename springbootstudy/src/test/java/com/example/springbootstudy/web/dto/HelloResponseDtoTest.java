package com.example.springbootstudy.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void lombok_test(){
        String name = "test";
        int mount = 1000;

        HelloResponseDto helloResponseDto = new HelloResponseDto(name, mount);

        assertThat(helloResponseDto.getName()).isEqualTo(name);
        assertThat(helloResponseDto.getMount()).isEqualTo(mount);
    }

}
