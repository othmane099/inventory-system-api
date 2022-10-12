package com.ombdev.inventorysystemapi.model;

import com.ombdev.inventorysystemapi.response.sale.CreateSaleResponse;
import com.ombdev.inventorysystemapi.response.sale.SaleResponse;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Getter @Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;
    private Double tax;
    private Double netPrice;
    private Double totalPrice;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @ManyToMany
    @JoinTable(
            name = "sale_product",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new TreeSet<>();

    @ManyToOne
    private Customer customer;


    public static CreateSaleResponse toCreateSaleResponse(Sale sale){
        if (sale == null) return null;
        return new CreateSaleResponse(
                sale.getId(),
                sale.getCode(),
                sale.getTax(),
                sale.getNetPrice(),
                sale.getTotalPrice(),
                sale.getPaymentMethod(),
                sale.getCreated_at()
        );
    }

    public static SaleResponse toSaleResponse(Sale sale){
        if (sale == null) return null;
        return new SaleResponse(
                sale.getId(),
                sale.getCode(),
                sale.getTax(),
                sale.getNetPrice(),
                sale.getTotalPrice(),
                sale.getPaymentMethod(),
                sale.getCreated_at()
        );
    }


}
