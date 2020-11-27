package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {

    private String username;
    private String password;

    private String email;
    private String role;
    private String age;
}
