package com.ombdev.inventorysystemapi.response.sale;

import com.ombdev.inventorysystemapi.model.PaymentMethod;
import com.ombdev.inventorysystemapi.response.customer.CustomerResponse;
import com.ombdev.inventorysystemapi.response.soldProduct.SoldProductResponse;

import java.time.LocalDateTime;
import java.util.Set;

public record SaleResponse(
        Long id, String saleCode, Double totalPrice, PaymentMethod paymentMethod,
        LocalDateTime created_at, Set<SoldProductResponse> soldProducts, CustomerResponse customer
) {
}
