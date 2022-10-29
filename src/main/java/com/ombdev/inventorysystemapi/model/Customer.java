package com.ombdev.inventorysystemapi.model;

import com.ombdev.inventorysystemapi.response.customer.CustomerResponse;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Getter @Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String customerCode;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String address;
    private Integer purchases;
    private LocalDateTime lastPurchase;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;


    @OneToMany(mappedBy="customer")
    private Set<Sale> sales = new TreeSet<>();

    @Override
    public String toString() {
        return "Customer{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }



    public static CustomerResponse toCustomerResponse(Customer customer){
        if (customer == null) return null;
        return new CustomerResponse(
                customer.getId(),
                customer.getCustomerCode(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getPurchases(),
                customer.getLastPurchase(),
                customer.getCreated_at()
        );
    }
}
