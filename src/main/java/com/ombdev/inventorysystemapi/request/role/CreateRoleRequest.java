package com.ombdev.inventorysystemapi.request.role;

import com.ombdev.inventorysystemapi.model.Role;
import com.ombdev.inventorysystemapi.model.RoleName;

public record CreateRoleRequest(RoleName roleName) {

    public static Role toEntity(CreateRoleRequest request){
        if (request == null) return null;
        Role role = new Role();
        role.setRoleName(request.roleName());
        return role;
    }
}
