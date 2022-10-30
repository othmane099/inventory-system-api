package com.ombdev.inventorysystemapi.request.soldProduct;

import com.ombdev.inventorysystemapi.model.SoldProduct;
import com.ombdev.inventorysystemapi.request.product.ProductRequest;

public record SoldProductRequest(Long id, Integer quantityTaken, Double totalPrice, ProductRequest product){

    public static SoldProduct toEntity(SoldProductRequest request){
        if (request == null) return null;
        SoldProduct soldProduct = new SoldProduct();
        soldProduct.setId(request.id());
        soldProduct.setQuantityTaken(request.quantityTaken());
        soldProduct.setTotalPrice(request.totalPrice());
        soldProduct.setProduct(ProductRequest.toEntity(request.product));
        return soldProduct;
    }
}
