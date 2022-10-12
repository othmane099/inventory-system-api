package com.ombdev.inventorysystemapi.response.product;

import com.ombdev.inventorysystemapi.response.category.CategoryResponse;

import java.time.LocalDateTime;
import java.util.Set;

public record CreateProductResponse(
        Long id, String code, String description, Integer quantity, Double buyingPrice,
        Double sellingPrice, LocalDateTime created_at, Set<CategoryResponse> categories
) {
}
