package com.example.demo.service;


import com.example.demo.domain.Member;
import org.springframework.stereotype.Service;


public interface MemberService {
    void createUser(Member member);

}
