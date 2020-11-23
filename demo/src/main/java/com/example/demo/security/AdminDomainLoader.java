package com.example.demo.security;

import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@RequiredArgsConstructor
public class AdminDomainLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final MemberRepository memberRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
