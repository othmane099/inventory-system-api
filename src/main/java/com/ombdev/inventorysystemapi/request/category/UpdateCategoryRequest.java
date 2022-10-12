package com.ombdev.inventorysystemapi.request.category;

import com.ombdev.inventorysystemapi.model.Category;

public record UpdateCategoryRequest(Long id, String name) {

    public static Category toEntity(UpdateCategoryRequest request){
        if (request == null) return null;
        Category category = new Category();
        category.setId(request.id());
        category.setName(request.name());

        return category;
    }
}
