package com.ombdev.inventorysystemapi.request.category;

import com.ombdev.inventorysystemapi.model.Category;

public record CreateCategoryRequest(String name) {

    public static Category toEntity(CreateCategoryRequest request){
        if (request == null) return null;
        Category category = new Category();
        category.setName(request.name());
        return category;
    }
}
