package com.ombdev.inventorysystemapi.request;

import com.ombdev.inventorysystemapi.model.Role;
import com.ombdev.inventorysystemapi.model.RoleName;

public record RoleRequest(Long id, RoleName roleName) {


    public static Role toEntity(RoleRequest request){
        if (request == null) return null;
        Role role = new Role();
        role.setId(request.id());
        role.setRoleName(request.roleName());
        return role;
    }
}
