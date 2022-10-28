package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Role;
import com.ombdev.inventorysystemapi.repository.RoleRepository;
import com.ombdev.inventorysystemapi.request.role.RoleRequest;
import com.ombdev.inventorysystemapi.response.role.RoleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleResponse createRole(Role role){
        return Role.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> index(){

        return roleRepository.findAll()
                .stream()
                .map(Role::toRoleResponse)
                .collect(Collectors.toList());
    }

    public List<RoleResponse> getAll(){

        return roleRepository.findAll()
                .stream()
                .map(Role::toRoleResponse)
                .collect(Collectors.toList());
    }
}
