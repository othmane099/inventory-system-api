package com.ombdev.inventorysystemapi.response;

import java.time.LocalDateTime;
import java.util.Set;

public record UserResponse(Long id,
                           String fullName,
                           String username,
                           String password,
                           String photo,
                           Boolean status,
                           LocalDateTime lastLogin,
                           LocalDateTime created_at,
                           Set<RoleResponse> roles) {
}
