package com.ombdev.inventorysystemapi.repository;

import com.ombdev.inventorysystemapi.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
