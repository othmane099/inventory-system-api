package com.ombdev.inventorysystemapi.request.customer;

import com.ombdev.inventorysystemapi.model.Customer;

public record CreateCustomerRequest(String fullName, String email, String phone, String address) {

    public static Customer toEntity(CreateCustomerRequest request){
        if (request == null) return null;
        Customer customer = new Customer();
        customer.setFullName(request.fullName());
        customer.setAddress(request.address());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        return customer;
    }
}
