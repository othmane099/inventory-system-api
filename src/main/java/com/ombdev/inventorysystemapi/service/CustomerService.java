package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Customer;
import com.ombdev.inventorysystemapi.model.Product;
import com.ombdev.inventorysystemapi.model.SortBy;
import com.ombdev.inventorysystemapi.repository.CustomerRepository;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.customer.CustomerResponse;
import com.ombdev.inventorysystemapi.response.product.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Page<CustomerResponse> index(String keyword, int page, int size, SortBy sortBy){

        Page<Customer> customersPage;

        switch (sortBy){
            case CUSTOMER_CODE_ASC -> customersPage = customerRepository
                    .findAllByCustomerCodeContainingOrFullNameContainingOrderByCustomerCodeAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case CUSTOMER_CODE_DESC -> customersPage = customerRepository
                    .findAllByCustomerCodeContainingOrFullNameContainingOrderByCustomerCodeDesc
                            (keyword, keyword, PageRequest.of(page, size));
            case CUSTOMER_FULL_NAME_ASC -> customersPage = customerRepository
                    .findAllByCustomerCodeContainingOrFullNameContainingOrderByFullNameAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case CUSTOMER_FULL_NAME_DESC -> customersPage = customerRepository
                    .findAllByCustomerCodeContainingOrFullNameContainingOrderByFullNameDesc
                            (keyword, keyword, PageRequest.of(page, size));
            case CUSTOMER_PHONE_ASC -> customersPage = customerRepository
                    .findAllByCustomerCodeContainingOrFullNameContainingOrderByPhoneAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case CUSTOMER_PHONE_DESC -> customersPage = customerRepository
                    .findAllByCustomerCodeContainingOrFullNameContainingOrderByPhoneDesc
                            (keyword, keyword, PageRequest.of(page, size));
            case CUSTOMER_EMAIL_ASC -> customersPage = customerRepository
                    .findAllByCustomerCodeContainingOrFullNameContainingOrderByEmailAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case CUSTOMER_EMAIL_DESC -> customersPage = customerRepository
                    .findAllByCustomerCodeContainingOrFullNameContainingOrderByEmailDesc
                            (keyword, keyword, PageRequest.of(page, size));
            default ->  customersPage = customerRepository
                    .findAllByCustomerCodeContainingOrFullNameContainingOrderByIdDesc
                            (keyword, keyword, PageRequest.of(page, size));
        }

        List<CustomerResponse> customers = customersPage
                .stream()
                .map(Customer::toCustomerResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(customers, PageRequest.of(page, size), customersPage.getTotalElements());
    }

    public CustomerResponse store(Customer customer){
        int randomNumber = new Random().nextInt(9000) + 1000;
        customer.setCustomerCode("c"+ randomNumber);
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

    public List<CustomerResponse> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(Customer::toCustomerResponse)
                .collect(Collectors.toList());
    }
}
