package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberDto;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String signIn(@RequestParam(value = "error", required = false)String error,
                         @RequestParam(value = "exception", required = false)String exception, Model model){

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/signIn";
    }
}
