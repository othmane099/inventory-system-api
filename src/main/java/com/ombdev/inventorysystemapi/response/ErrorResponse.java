package com.ombdev.inventorysystemapi.response;

import com.ombdev.inventorysystemapi.model.ErrorCode;

import java.util.List;

public record ErrorResponse(Integer httpCode, ErrorCode errorCode, String message, List<String> errors) {
}
