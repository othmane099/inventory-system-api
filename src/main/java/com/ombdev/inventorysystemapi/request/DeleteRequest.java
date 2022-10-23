package com.ombdev.inventorysystemapi.request;

public record DeleteRequest(Long id) {

    public static Long getIds(DeleteRequest request){
        if (request == null) return null;
        return request.id();
    }

}
