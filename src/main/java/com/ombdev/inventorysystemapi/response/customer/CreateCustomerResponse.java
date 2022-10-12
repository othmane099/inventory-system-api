package com.ombdev.inventorysystemapi.response.customer;

import java.time.LocalDateTime;

public record CreateCustomerResponse(
        Long id, String fullName, String email, String phone, String address, Integer purchases, LocalDateTime created_at
) {
}
