package com.ombdev.inventorysystemapi.response.user;

import com.ombdev.inventorysystemapi.response.role.RoleResponse;

import java.time.LocalDateTime;
import java.util.Set;

public record CreateUserResponse(Long id,
                           String fullName,
                           String username,
                           String password,
                           Boolean status,
                           LocalDateTime created_at,
                           Set<RoleResponse> roles) {
}
