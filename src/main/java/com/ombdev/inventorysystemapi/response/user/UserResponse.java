package com.ombdev.inventorysystemapi.response.user;

import com.ombdev.inventorysystemapi.response.role.RoleResponse;

import java.time.LocalDateTime;
import java.util.Set;

public record UserResponse(Long id, String slug,
                           String fullName,
                           String username,
                           String password,
                           String photo,
                           Boolean status,
                           LocalDateTime lastLogin,
                           LocalDateTime created_at,
                           Set<RoleResponse> roles) {
}
