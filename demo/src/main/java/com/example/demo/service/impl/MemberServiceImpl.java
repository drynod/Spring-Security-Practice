package com.example.demo.service.impl;

import com.example.demo.domain.Member;
import com.example.demo.domain.Role;
import com.example.demo.dto.MemberDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.modelmbean.ModelMBeanAttributeInfo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(Member member) {
        Role role =roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        member.setMemberRoles(roles);
        memberRepository.save(member);
    }

    @Override
    public void modifyUser(MemberDto memberDto) {
        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberDto, Member.class);

        if(memberDto.getRole() != null){
            Set<Role> roles = new HashSet<>();
            for (String role : memberDto.getRoles()) {
                Role r = roleRepository.findByRoleName(role);
                roles.add(r);
            }

            member.setMemberRoles(roles);
        }
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
    }

    @Transactional
    @Override
    public MemberDto getMember(Long id){
        Member member = memberRepository.findById(id).orElse(new Member());
        ModelMapper modelMapper = new ModelMapper();
        MemberDto memberDto = modelMapper.map(member, MemberDto.class);

        List<String> roles = member.getMemberRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toList());

        memberDto.setRoles(roles);
        return memberDto;
    }

    @Override
    public void orders() {

    }

    @Transactional
    @Override
    public List<Member> getMembers(){
        return memberRepository.findAll();
    }

    @Override
    public void deleteMembers(Long id) {
        memberRepository.deleteById(id);
    }


}
