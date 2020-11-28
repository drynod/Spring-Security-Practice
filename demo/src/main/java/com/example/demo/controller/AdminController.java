package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;
    private final RoleService roleService;


    @GetMapping("/admin")
    public String config() throws Exception{
        return "admin/config";
    }

    @GetMapping("/admin/members")
    public String getMembers(Model model) throws Exception{
        List<Member> members = memberService.getMembers();
        model.addAttribute("members", members);

        return "admin/member/list";
    }

}
