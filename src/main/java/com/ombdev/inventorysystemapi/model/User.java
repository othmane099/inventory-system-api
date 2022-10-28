package com.ombdev.inventorysystemapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ombdev.inventorysystemapi.response.user.UserResponse;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String username;
    private String phone;
    private String email;
    private String password;
    private String photo;
    private Boolean status;
    private LocalDateTime lastLogin;
    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime created_at;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new TreeSet<>();

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public static UserResponse toUserResponse(User user){
        if (user == null) return null;
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoto(),
                user.getStatus(),
                user.getRoles() != null ?
                        user.getRoles().stream()
                                .map(Role::toRoleResponse)
                                .collect(Collectors.toSet()) : null
        );
    }
}


