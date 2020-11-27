package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter @Setter
public class BoardDto {
    private String title;
    private String author;
    private LocalDate localDateTime;
    private String content;
    private String password;



}
