package com.ombdev.inventorysystemapi.repository;

import com.ombdev.inventorysystemapi.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAll(Pageable pageable);
    Page<Category> findAllByCategoryCodeContainingOrCategoryNameContainingOrderByIdDesc(String categoryCode, String categoryName, Pageable pageable);
}
