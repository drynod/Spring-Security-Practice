package com.example.demo.service;


import com.example.demo.domain.Member;
import com.example.demo.dto.MemberDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MemberService {
    void createUser(Member member);

    void modifyUser(MemberDto memberDto);

    List<Member> getMembers();

    void deleteMembers(Long id);

    MemberDto getMember(Long id);

    void orders();
}
