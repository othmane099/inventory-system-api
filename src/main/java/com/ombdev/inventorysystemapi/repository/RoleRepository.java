package com.ombdev.inventorysystemapi.repository;

import com.ombdev.inventorysystemapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
