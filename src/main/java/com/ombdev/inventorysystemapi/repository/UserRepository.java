package com.ombdev.inventorysystemapi.repository;

import com.ombdev.inventorysystemapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
