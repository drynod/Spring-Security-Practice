package com.example.demo.service;

import com.example.demo.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    Role getRole(Long id);
    List<Role> getRoles();
    void createRole(Role role);
    void deleteRole(Long id);
}
