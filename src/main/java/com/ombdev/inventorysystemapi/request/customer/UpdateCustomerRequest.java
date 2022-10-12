package com.ombdev.inventorysystemapi.request.customer;

import com.ombdev.inventorysystemapi.model.Customer;

public record UpdateCustomerRequest(
        Long id, String fullName, String email, String phone, String address
) {

    public static Customer toEntity(UpdateCustomerRequest request){
        if (request == null) return null;
        Customer customer = new Customer();
        customer.setId(request.id());
        customer.setFullName(request.fullName());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());

        return customer;
    }
}
