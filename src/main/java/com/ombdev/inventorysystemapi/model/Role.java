package com.ombdev.inventorysystemapi.model;

import com.ombdev.inventorysystemapi.response.RoleResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Getter @Setter
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new TreeSet<>();

    public static RoleResponse toRoleResponse(Role role){
        if (role == null) return null;
        return new RoleResponse(
                role.getId(),
                role.getRoleName()
        );
    }
}
