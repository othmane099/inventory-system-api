package com.ombdev.inventorysystemapi.model;

import com.ombdev.inventorysystemapi.response.sale.SaleResponse;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String saleCode;
    private Double tax;
    private Double netPrice;
    private Double totalPrice;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @OneToMany(mappedBy="sale")
    private Set<SoldProduct> soldProducts = new TreeSet<>();

    @ManyToOne
    private Customer customer;


    public static SaleResponse toSaleResponse(Sale sale){
        if (sale == null) return null;
        return new SaleResponse(
                sale.getId(),
                sale.getSaleCode(),
                sale.getTotalPrice(),
                sale.getPaymentMethod(),
                sale.getCreated_at(),
                sale.getSoldProducts() != null ?
                        sale.getSoldProducts().stream()
                                .map(SoldProduct::toSoldProductResponse)
                                .collect(Collectors.toSet()) : null,
                Customer.toCustomerResponse(sale.getCustomer())

        );
    }


}
