package com.example.demo.service;

import com.example.demo.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
public class MemberContext extends User {

    private final Member member;

    public MemberContext(Member member, List<GrantedAuthority> roles) {
        super(member.getUsername(), member.getPassword(), roles);
        this.member = member;
    }

}
