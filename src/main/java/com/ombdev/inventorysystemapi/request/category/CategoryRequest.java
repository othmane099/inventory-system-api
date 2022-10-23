package com.ombdev.inventorysystemapi.request.category;

import com.ombdev.inventorysystemapi.model.Category;

public record CategoryRequest(Long id, String categoryCode, String categoryName) {


    public static Category toEntity(CategoryRequest request){
        if (request == null) return null;
        Category category = new Category();
        category.setId(request.id());
        category.setCategoryCode(request.categoryCode());
        category.setCategoryName(request.categoryName());
        return category;
    }
}
