package com.ombdev.inventorysystemapi.repository;

import com.ombdev.inventorysystemapi.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findAllByCustomerCodeContainingOrFullNameContainingOrderByIdDesc(String customerCode, String fullName, Pageable pageable);
    Page<Customer> findAllByCustomerCodeContainingOrFullNameContainingOrderByCustomerCodeAsc(String customerCode, String fullName, Pageable pageable);
    Page<Customer> findAllByCustomerCodeContainingOrFullNameContainingOrderByCustomerCodeDesc(String customerCode, String fullName, Pageable pageable);
    Page<Customer> findAllByCustomerCodeContainingOrFullNameContainingOrderByFullNameAsc(String customerCode, String fullName, Pageable pageable);
    Page<Customer> findAllByCustomerCodeContainingOrFullNameContainingOrderByFullNameDesc(String customerCode, String fullName, Pageable pageable);
    Page<Customer> findAllByCustomerCodeContainingOrFullNameContainingOrderByPhoneAsc(String customerCode, String fullName, Pageable pageable);
    Page<Customer> findAllByCustomerCodeContainingOrFullNameContainingOrderByPhoneDesc(String customerCode, String fullName, Pageable pageable);
    Page<Customer> findAllByCustomerCodeContainingOrFullNameContainingOrderByEmailAsc(String customerCode, String fullName, Pageable pageable);
    Page<Customer> findAllByCustomerCodeContainingOrFullNameContainingOrderByEmailDesc(String customerCode, String fullName, Pageable pageable);
}
