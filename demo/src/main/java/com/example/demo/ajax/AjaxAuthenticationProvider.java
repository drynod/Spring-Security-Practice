package com.example.demo.ajax;

import com.example.demo.security.token.AjaxAuthenticationToken;
import com.example.demo.service.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


public class AjaxAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, memberContext.getPassword())) {
            throw new BadCredentialsException("BadCredentialsException");
        }

        return new AjaxAuthenticationToken(memberContext.getMember(), null, memberContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AjaxAuthenticationToken.class);
    }
}
