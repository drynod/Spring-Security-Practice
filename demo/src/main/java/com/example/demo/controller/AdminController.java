package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.Resources;
import com.example.demo.domain.Role;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.ResourcesDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.metadata.UrlFilterInvocationSecurityMetaDataSource;
import com.example.demo.repository.ResourcesRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.ResourcesService;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sun.reflect.annotation.ExceptionProxy;

import javax.management.modelmbean.ModelMBeanAttributeInfo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final ResourcesService resourcesService;
    private final UrlFilterInvocationSecurityMetaDataSource filterInvocationSecurityMetaDataSource;


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

    @PostMapping("/admin/members")
    public String modifyMember(MemberDto memberDto){
        memberService.modifyUser(memberDto);

        return "redirect:/admin/members";
    }

    @GetMapping("/admin/members/{id}")
    public String memberDetail(@PathVariable(value = "id") Long id, Model model){
        MemberDto memberDto = memberService.getMember(id);
        List<Role> roleList = roleService.getRoles();

        model.addAttribute("memberDto", memberDto);
        model.addAttribute("roleList", roleList);

        return "admin/member/detail";
    }



    @GetMapping("/admin/members/delete/{id}")
    public String deleteMember(@PathVariable Long id, Model model){
        memberService.deleteMembers(id);
        return "redirect:/admin/members";
    }



    /*리소스 컨트롤러 */

    @GetMapping("/admin/resources")
    public String getResources(Model model) throws Exception{

        List<Resources> resources = resourcesService.getResources();
        model.addAttribute("resources", resources);

        return "admin/resource/list";
    }

    @PostMapping("/admin/resources")
    public String createResources(ResourcesDto resourcesDto) throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Resources resources = modelMapper.map(resourcesDto, Resources.class);
        resources.setRoleSet(roles);

        resourcesService.createResources(resources);
        filterInvocationSecurityMetaDataSource.reload();


        return "redirect:/admin/resources";
    }


    @GetMapping("/admin/resources/register")
    public String viewResources(Model model) throws Exception{
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roleList", roles);

        ResourcesDto resourcesDto = new ResourcesDto();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role());
        resourcesDto.setRoleSet(roleSet);

        model.addAttribute("resources", resourcesDto);
        return "admin/resource/detail";
    }


    @GetMapping("/admin/resources/{id}")
    public String getResources(@PathVariable String id, Model model) throws Exception{
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);
        Resources resources = resourcesService.getResources(Long.valueOf(id));

        ModelMapper modelMapper = new ModelMapper();
        ResourcesDto resourcesDto = modelMapper.map(resources, ResourcesDto.class);
        model.addAttribute("resources", resourcesDto);

        return "admin/resource/detail";
    }


    /*
    Role 컨트롤러
    */

    @GetMapping("/admin/roles")
    public String getRoles(Model model) throws Exception{
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roles", roleList);

        return "admin/role/list";
    }


    @PostMapping("/admin/roles")
    public String createRole(RoleDto roleDto) throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDto, Role.class);
        roleService.createRole(role);

        return "redirect:/admin/roles";
    }

    @GetMapping("/admin/roles/register")
    public String viewRoles(Model model) throws Exception{
        RoleDto roleDto = new RoleDto();
        model.addAttribute("role", roleDto);

        return "admin/role/detail";
    }

    @GetMapping("/admin/roles/{id}")
    public String getRole(@PathVariable Long id, Model model){
        Role role = roleService.getRole(id);

        ModelMapper modelMapper = new ModelMapper();
        RoleDto roleDto = modelMapper.map(role, RoleDto.class);
        model.addAttribute("role", role);

        return "admin/role/detail";
    }




}
