package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.Role;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public void createUser(Member member) {
        Role role =roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        member.setMemberRoles(roles);
        memberRepository.save(member);
    }
}
