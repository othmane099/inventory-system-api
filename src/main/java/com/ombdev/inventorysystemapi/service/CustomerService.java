package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Customer;
import com.ombdev.inventorysystemapi.repository.CustomerRepository;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.customer.CreateCustomerRequest;
import com.ombdev.inventorysystemapi.request.customer.UpdateCustomerRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.customer.CreateCustomerResponse;
import com.ombdev.inventorysystemapi.response.customer.CustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerResponse> index(){
        return customerRepository.findAll()
                .stream()
                .map(Customer::toCustomerResponse)
                .collect(Collectors.toList());
    }

    public CreateCustomerResponse createCustomer(CreateCustomerRequest request){
        Customer customer = CreateCustomerRequest.toEntity(request);
        customer.setPurchases(0);
        return Customer.toCreateCustomerResponse(customerRepository.save(customer));
    }

    public CustomerResponse show(ShowRequest request){
        Customer customer = customerRepository.findById(request.id()).get();
        return Customer.toCustomerResponse(customer);
    }

    public CustomerResponse update(UpdateCustomerRequest request) {
        Customer customer = UpdateCustomerRequest.toEntity(request);
        return Customer.toCustomerResponse(customerRepository.save(customer));
    }

    public DeleteResponse delete(DeleteRequest request) {
        customerRepository.deleteById(request.id());
        return new DeleteResponse("Customer deleted successfully :)");
    }
}
