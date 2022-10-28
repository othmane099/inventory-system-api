package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.request.role.RoleRequest;
import com.ombdev.inventorysystemapi.response.role.RoleResponse;
import com.ombdev.inventorysystemapi.service.RoleService;
import com.ombdev.inventorysystemapi.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(Constants.CLIENT_BASE_URL)
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

    @GetMapping("/admin/roles/all")
    public List<RoleResponse> allRoles(){
        return roleService.getAll();
    }
}
