package com.ombdev.inventorysystemapi.request.customer;

import com.ombdev.inventorysystemapi.model.Customer;

import java.time.LocalDateTime;

public record CustomerRequest(Long id, String fullName, String email, String phone, String address, Integer purchases,
                              LocalDateTime lastPurchase, LocalDateTime created_at) {


    public static Customer toEntity(CustomerRequest request){

        if(request == null) return  null;
        Customer customer = new Customer();
        customer.setId(request.id());
        customer.setFullName(request.fullName());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());
        customer.setPurchases(request.purchases());
        customer.setLastPurchase(request.lastPurchase());
        customer.setCreated_at(request.created_at());

        return customer;
    }
}
