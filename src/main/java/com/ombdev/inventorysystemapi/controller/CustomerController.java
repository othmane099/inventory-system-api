package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.customer.CreateCustomerRequest;
import com.ombdev.inventorysystemapi.request.customer.UpdateCustomerRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.customer.CreateCustomerResponse;
import com.ombdev.inventorysystemapi.response.customer.CustomerResponse;
import com.ombdev.inventorysystemapi.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/seller/customers")
    public List<CustomerResponse> index(){
        return customerService.index();
    }

    @PostMapping("/seller/customers/create")
    public CreateCustomerResponse create(@RequestBody CreateCustomerRequest request){
        return customerService.createCustomer(request);
    }

    @GetMapping("/seller/customers/show")
    public CustomerResponse show(ShowRequest request){
        return customerService.show(request);
    }

    @DeleteMapping("/seller/customers/delete")
    public DeleteResponse delete(@RequestBody DeleteRequest request){
        return customerService.delete(request);
    }

    @PutMapping("/seller/customers/update")
    public CustomerResponse update(@RequestBody UpdateCustomerRequest request){
        return customerService.update(request);
    }
}
