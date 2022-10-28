package com.ombdev.inventorysystemapi.request.user;

import com.ombdev.inventorysystemapi.model.User;
import com.ombdev.inventorysystemapi.request.role.RoleRequest;

import java.util.Set;
import java.util.stream.Collectors;

public record UserRequest(
        Long id, String fullName, String username, String email, String password, String phone, Boolean status, Set<RoleRequest> roles){

    public UserRequest {
        if (fullName.isBlank() || fullName == null){
            throw new IllegalArgumentException("FullName cannot be blank");
        }
    }

    public static User toEntity(UserRequest request){
        if (request == null) return null;
        User user = new User();
        user.setId(request.id());
        user.setFullName(request.fullName());
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setEmail(request.email());
        user.setPhone(request.phone());
        user.setStatus(request.status());
        user.setRoles(request.roles() != null ?
                request.roles().stream()
                        .map(RoleRequest::toEntity)
                        .collect(Collectors.toSet()) : null);
        return user;
    }

}
