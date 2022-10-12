package com.ombdev.inventorysystemapi.request.user;

import com.ombdev.inventorysystemapi.model.User;
import com.ombdev.inventorysystemapi.request.role.RoleRequest;

import java.util.Set;
import java.util.stream.Collectors;

public record CreateUserRequest(String fullName, String username, String password, Set<RoleRequest> roles){

    public CreateUserRequest {
        if (fullName.isBlank() || fullName == null){
            throw new IllegalArgumentException("FullName cannot be blank");
        }
    }

    public static User toEntity(CreateUserRequest request){
        if (request == null) return null;
        User user = new User();
        user.setFullName(request.fullName());
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setRoles(request.roles() != null ?
                request.roles().stream()
                        .map(RoleRequest::toEntity)
                        .collect(Collectors.toSet()) : null);
        return user;
    }

}
