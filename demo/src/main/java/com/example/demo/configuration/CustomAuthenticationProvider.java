package com.example.demo.configuration;

import com.example.demo.service.MemberContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(username);

        if(!passwordEncoder.matches(password, memberContext.getMember().getPassword())){
            throw new BadCredentialsException("BadCredentialsException");
        }

//      input 태그 속성 hidden 의 secret key 를 받아와서 파라미터로 보내줌.
//        FormWebAuthenticationDetails details = (FormWebAuthenticationDetails) authentication.getDetails();
//        String secretKey = details.getSecretKey();
//
//        if(secretKey == null || !"secret".equals(secretKey)){
//            throw new InsufficientAuthenticationException("InsufficientAuthenticationException");
//        }


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(memberContext.getMember(), null, memberContext.getAuthorities());

        return usernamePasswordAuthenticationToken;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
