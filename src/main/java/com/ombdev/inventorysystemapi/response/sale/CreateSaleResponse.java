package com.ombdev.inventorysystemapi.response.sale;

import com.ombdev.inventorysystemapi.model.PaymentMethod;

import java.time.LocalDateTime;

public record CreateSaleResponse(
        Long id, String code, Double tax, Double netPrice, Double totalPrice, PaymentMethod paymentMethod, LocalDateTime created_at
) {
}
