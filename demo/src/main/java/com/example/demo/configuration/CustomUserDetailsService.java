package com.example.demo.configuration;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);

        if(member == null){
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }


        Set<String> memberRoles = member.getMemberRoles()
                .stream()
                .map(memberRole -> memberRole.getRoleName())
                .collect(Collectors.toSet());


        List<GrantedAuthority> collect = memberRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new MemberContext(member, collect);
    }
}
