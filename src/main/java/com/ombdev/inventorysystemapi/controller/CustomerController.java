package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.customer.CustomerRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
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
    public CustomerResponse create(@RequestBody CustomerRequest request){
        return customerService.createCustomer(CustomerRequest.toEntity(request));
    }

    @GetMapping("/seller/customers/show")
    public CustomerResponse show(@RequestBody ShowRequest request){
        return customerService.show(request.id());
    }

    @DeleteMapping("/seller/customers/delete")
    public DeleteResponse delete(@RequestBody DeleteRequest request){
        return customerService.delete(request.id());
    }

    @PutMapping("/seller/customers/update")
    public CustomerResponse update(@RequestBody CustomerRequest request){
        return customerService.update(CustomerRequest.toEntity(request));
    }
}
