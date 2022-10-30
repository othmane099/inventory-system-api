package com.ombdev.inventorysystemapi.response.soldProduct;

import com.ombdev.inventorysystemapi.response.product.ProductResponse;

public record SoldProductResponse(Long id, Integer quantityTaken, Double totalPrice, ProductResponse product) {
}
