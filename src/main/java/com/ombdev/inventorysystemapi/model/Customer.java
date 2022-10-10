package com.ombdev.inventorysystemapi.model;

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
}
