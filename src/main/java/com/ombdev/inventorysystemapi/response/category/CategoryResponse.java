package com.ombdev.inventorysystemapi.response.category;


import java.time.LocalDateTime;

public record CategoryResponse(Long id, String name, String slug, LocalDateTime created_at) {
}
