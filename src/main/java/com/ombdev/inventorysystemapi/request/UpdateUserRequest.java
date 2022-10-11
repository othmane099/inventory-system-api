package com.ombdev.inventorysystemapi.request;

import com.ombdev.inventorysystemapi.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public record UpdateUserRequest(
        Long id, String fullName, String username, String password, Boolean status, Set<RoleRequest> roles){

    public UpdateUserRequest {
        if (fullName.isBlank() || fullName == null){
            throw new IllegalArgumentException("FullName cannot be blank");
        }
    }

    public static User toEntity(UpdateUserRequest request){
        if (request == null) return null;
        User user = new User();
        user.setId(request.id());
        user.setFullName(request.fullName());
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setStatus(request.status());
        user.setRoles(request.roles() != null ?
                request.roles().stream()
                        .map(RoleRequest::toEntity)
                        .collect(Collectors.toSet()) : null);
        return user;
    }

}
