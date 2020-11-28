package com.example.demo.listener;


import com.example.demo.domain.Member;
import com.example.demo.domain.Resources;
import com.example.demo.domain.Role;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ResourcesRepository;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final ResourcesRepository resourcesRepository;

    private final PasswordEncoder passwordEncoder;

    private static AtomicInteger count = new AtomicInteger(0);


    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        setupSecurityResources();

        alreadySetup = true;
    }


    private void setupSecurityResources() {
        Set<Role> roles = new HashSet<>();
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자");
        roles.add(adminRole);
        createResourceIfNotFound("/admin/**", "", roles, "url");

        Role managerRole = createRoleIfNotFound("ROLE_MANAGER", "매니저권한");
        Role memberRole = createRoleIfNotFound("ROLE_USER", "사용자권한");

        Member member = createMemberIfNotFound("admin", "pass", "admin@gmail.com", "10", roles);
    }

    @Transactional
    public Role createRoleIfNotFound(String roleName, String roleDesc) {
        Role role = roleRepository.findByRoleName(roleName);

        if (role == null) {
            role = Role.builder()
                    .roleName(roleName)
                    .roleDesc(roleDesc)
                    .build();
        }
        return roleRepository.save(role);
    }

    @Transactional
    public Member createMemberIfNotFound(final String userName, final String password,
                                         final String email, final String age,  Set<Role> roleSet) {
        Member member = memberRepository.findByUsername(userName);

        if(member == null){
            member = Member.builder()
                    .username(userName)
                    .email(email)
                    .age(age)
                    .password(passwordEncoder.encode(password))
                    .memberRoles(roleSet)
                    .build();
        }
        return memberRepository.save(member);
    }


    @Transactional
    public Resources createResourceIfNotFound(String resourceName, String httpMethod, Set<Role> roleSet,
                                              String resourceType){
        Resources resources = resourcesRepository.findByResourceNameAndHttpMethod(resourceName, httpMethod);


        if(resources == null){
            resources = Resources.builder()
                    .resourceName(resourceName)
                    .httpMethod(httpMethod)
                    .roleSet(roleSet)
                    .resourceType(resourceType)
                    .orderNum(count.incrementAndGet())
                    .build();
        }
        return resourcesRepository.save(resources);
    }


}
