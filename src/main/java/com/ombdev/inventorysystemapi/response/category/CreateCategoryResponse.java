package com.ombdev.inventorysystemapi.response.category;

import com.ombdev.inventorysystemapi.model.Category;
import com.ombdev.inventorysystemapi.request.category.CreateCategoryRequest;

import java.time.LocalDateTime;

public record CreateCategoryResponse(Long id, String name, LocalDateTime created_at) {

}
