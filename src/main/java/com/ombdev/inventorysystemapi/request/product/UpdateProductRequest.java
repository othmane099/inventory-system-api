package com.ombdev.inventorysystemapi.request.product;

import com.ombdev.inventorysystemapi.model.Product;
import com.ombdev.inventorysystemapi.request.category.CategoryRequest;

import java.util.Set;
import java.util.stream.Collectors;

public record UpdateProductRequest(
        Long id, String code, String description, Integer quantity, Double buyingPrice,
        Double sellingPrice, Set<CategoryRequest> categories

) {

    public static Product toEntity(UpdateProductRequest request){
        if (request == null) return null;
        Product product = new Product();
        product.setId(request.id());
        product.setCode(request.code());
        product.setDescription(request.description());
        product.setQuantity(request.quantity());
        product.setBuyingPrice(request.buyingPrice());
        product.setSellingPrice(request.sellingPrice());
        product.setCategories(request.categories() != null ?
                request.categories().stream()
                        .map(CategoryRequest::toEntity)
                        .collect(Collectors.toSet()) : null);
        return product;
    }
}
