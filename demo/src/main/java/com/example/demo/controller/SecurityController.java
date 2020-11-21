package com.example.demo.controller;

import com.example.demo.domain.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {
    @GetMapping(value = {"/denied", "/api/denied"})
    public String accessDenied(@RequestParam(value = "exception", required = false) String exception, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member principal = (Member) authentication.getPrincipal();

        model.addAttribute("username", principal.getUsername());
        model.addAttribute("exception", exception);

        return "denied";
    }
}
