package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.request.role.RoleRequest;
import com.ombdev.inventorysystemapi.response.role.RoleResponse;
import com.ombdev.inventorysystemapi.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/admin/roles/create")
    public RoleResponse create(@RequestBody RoleRequest request){
        return roleService.createRole(RoleRequest.toEntity(request));
    }

    @GetMapping("/admin/roles")
    public List<RoleResponse> index(){
        return roleService.index();
    }
}
