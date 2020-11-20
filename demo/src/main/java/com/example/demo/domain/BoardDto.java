package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter @Setter
public class BoardDto {
    private String title;
    private String author;
    private LocalDateTime localDateTime;
    private String content;
    private String password;
}
