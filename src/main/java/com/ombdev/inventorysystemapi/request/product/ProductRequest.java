package com.ombdev.inventorysystemapi.request.product;

import com.ombdev.inventorysystemapi.model.Product;

public record ProductRequest(
        Long id, String code, String description, Integer quantity, Double buyingPrice,
        Double sellingPrice
) {

    public static Product toEntity(ProductRequest request){
        if (request == null) return null;
        Product product = new Product();
        product.setId(request.id());
        product.setCode(request.code());
        product.setDescription(request.description());
        product.setQuantity(request.quantity());
        product.setBuyingPrice(request.buyingPrice());
        product.setSellingPrice(request.sellingPrice());
        return product;
    }
}
