package com.ombdev.inventorysystemapi.request.category;

import com.ombdev.inventorysystemapi.model.Category;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Validated
public record CategoryRequest(Long id, String name, String slug, LocalDateTime created_at) {


    public static Category toEntity(CategoryRequest request){
        if (request == null) return null;
        Category category = new Category();
        category.setId(request.id());
        category.setName(request.name());
        category.setCreated_at(request.created_at());
        return category;
    }
}
