package com.ombdev.inventorysystemapi.repository;

import com.ombdev.inventorysystemapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByFullNameContainingOrUsernameContainingOrderByIdDesc(String fullName, String username, Pageable pageable);
    Page<User> findAllByFullNameContainingOrUsernameContainingOrderByFullNameAsc(String fullName, String username, Pageable pageable);
    Page<User> findAllByFullNameContainingOrUsernameContainingOrderByFullNameDesc(String fullName, String username, Pageable pageable);
    Page<User> findAllByFullNameContainingOrUsernameContainingOrderByUsernameAsc(String fullName, String username, Pageable pageable);
    Page<User> findAllByFullNameContainingOrUsernameContainingOrderByUsernameDesc(String fullName, String username, Pageable pageable);
    Page<User> findAllByFullNameContainingOrUsernameContainingOrderByEmailAsc(String fullName, String username, Pageable pageable);
    Page<User> findAllByFullNameContainingOrUsernameContainingOrderByEmailDesc(String fullName, String username, Pageable pageable);
    Page<User> findAllByFullNameContainingOrUsernameContainingOrderByPhoneAsc(String fullName, String username, Pageable pageable);
    Page<User> findAllByFullNameContainingOrUsernameContainingOrderByPhoneDesc(String fullName, String username, Pageable pageable);

}
