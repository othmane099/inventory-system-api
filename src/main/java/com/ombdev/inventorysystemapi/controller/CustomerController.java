package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.model.SortBy;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.customer.CustomerRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.customer.CustomerResponse;
import com.ombdev.inventorysystemapi.service.CustomerService;
import com.ombdev.inventorysystemapi.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(Constants.CLIENT_BASE_URL)
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/seller/customers/all")
    public List<CustomerResponse> allCustomers(){
        return customerService.getAll();
    }

    @GetMapping("/seller/customers")
    public Page<CustomerResponse> index(@RequestParam String keyword,
                                        @RequestParam int page,
                                        @RequestParam int size,
                                        @RequestParam SortBy sortBy){
        return customerService.index(keyword, page, size, sortBy);
    }

    @PostMapping("/seller/customers/store")
    public CustomerResponse store(@RequestBody CustomerRequest request){
        return customerService.store(CustomerRequest.toEntity(request));
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
