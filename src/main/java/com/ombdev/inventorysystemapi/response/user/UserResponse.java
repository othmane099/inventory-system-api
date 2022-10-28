package com.ombdev.inventorysystemapi.response.user;

import com.ombdev.inventorysystemapi.response.role.RoleResponse;

import java.util.Set;

public record UserResponse(Long id,
                           String fullName,
                           String username,
                           String phone,
                           String email,
                           String password,
                           String photo,
                           Boolean status,
                           Set<RoleResponse> roles) {
}
