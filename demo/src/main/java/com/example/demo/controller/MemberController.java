package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberDto;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String Home(){
        return "home";
    }

    @GetMapping("/signIn")
    public String signIn(){
        return "login";
    }

    @GetMapping("/myPage")
    public String myPage(){
        return "menu/myPage";
    }

    @GetMapping("/signUp")
    public String signUp(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String createMember(MemberDto memberDto){

        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberDto, Member.class);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole("ROLE_USER");
        memberService.createUser(member);

        return "redirect:/signIn";
    }


}
