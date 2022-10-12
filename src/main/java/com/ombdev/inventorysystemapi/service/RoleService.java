package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Role;
import com.ombdev.inventorysystemapi.repository.RoleRepository;
import com.ombdev.inventorysystemapi.request.role.CreateRoleRequest;
import com.ombdev.inventorysystemapi.response.role.RoleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleResponse createRole(CreateRoleRequest request){
        Role role = CreateRoleRequest.toEntity(request);
        return Role.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> getRoles(){

        return roleRepository.findAll()
                .stream()
                .map(Role::toRoleResponse)
                .collect(Collectors.toList());
    }
}
