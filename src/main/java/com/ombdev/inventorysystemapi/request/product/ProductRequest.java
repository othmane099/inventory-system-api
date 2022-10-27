package com.ombdev.inventorysystemapi.request.product;

import com.ombdev.inventorysystemapi.model.Product;
import com.ombdev.inventorysystemapi.request.category.CategoryRequest;

import java.util.Set;
import java.util.stream.Collectors;

public record ProductRequest(
        Long id, String productCode, String productName, Integer quantity, Double buyingPrice,
        Double sellingPrice, Set<CategoryRequest> categories

) {

    public static Product toEntity(ProductRequest request){
        if (request == null) return null;
        Product product = new Product();
        product.setId(request.id());
        product.setProductCode(request.productCode());
        product.setProductName(request.productName());
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
