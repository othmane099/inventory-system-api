package com.ombdev.inventorysystemapi.response.product;

import com.ombdev.inventorysystemapi.response.category.CategoryResponse;

import java.util.Set;

public record ProductResponse(Long id, String productName, String productCode, Set<CategoryResponse> categories, Double sellingPrice,
                              Double buyingPrice, Integer quantity) {
}
