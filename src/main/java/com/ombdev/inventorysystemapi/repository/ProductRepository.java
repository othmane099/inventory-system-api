package com.ombdev.inventorysystemapi.repository;

import com.ombdev.inventorysystemapi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByProductCodeContainingOrProductNameContainingOrderByIdDesc(String productCode, String productName, Pageable pageable);
    Page<Product> findAllByProductCodeContainingOrProductNameContainingOrderByProductCodeDesc(String productCode, String productName, Pageable pageable);
    Page<Product> findAllByProductCodeContainingOrProductNameContainingOrderByProductNameDesc(String productCode, String productName, Pageable pageable);
    Page<Product> findAllByProductCodeContainingOrProductNameContainingOrderByProductCodeAsc(String productCode, String productName, Pageable pageable);
    Page<Product> findAllByProductCodeContainingOrProductNameContainingOrderByProductNameAsc(String productCode, String productName, Pageable pageable);
}
