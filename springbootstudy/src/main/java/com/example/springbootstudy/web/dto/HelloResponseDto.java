package com.example.springbootstudy.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HelloResponseDto {
    private final String name;
    private final int mount;
}
