package com.ombdev.inventorysystemapi.response.customer;

import java.time.LocalDateTime;

public record CustomerResponse(
        Long id, String slug, String fullName, String email, String phone, String address,
        Integer purchases, LocalDateTime lastPurchase, LocalDateTime create_at
) {
}
