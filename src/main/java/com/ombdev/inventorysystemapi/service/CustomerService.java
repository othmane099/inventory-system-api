package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Customer;
import com.ombdev.inventorysystemapi.repository.CustomerRepository;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
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

    public CustomerResponse createCustomer(Customer customer){
        return Customer.toCustomerResponse(customerRepository.save(customer));
    }

    public CustomerResponse show(Long id){
        Customer customer = customerRepository.findById(id).get();
        return Customer.toCustomerResponse(customer);
    }

    public CustomerResponse update(Customer customer) {
        return Customer.toCustomerResponse(customerRepository.save(customer));
    }

    public DeleteResponse delete(Long id) {
        customerRepository.deleteById(id);
        return new DeleteResponse("Customer deleted successfully :)");
    }
}
