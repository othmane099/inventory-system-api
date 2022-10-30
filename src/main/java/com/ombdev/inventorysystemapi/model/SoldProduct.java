package com.ombdev.inventorysystemapi.model;

import com.ombdev.inventorysystemapi.response.soldProduct.SoldProductResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class SoldProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantityTaken;
    private Double totalPrice;
    @OneToOne
    private Product product;
    @ManyToOne
    private Sale sale;

    public static SoldProductResponse toSoldProductResponse(SoldProduct soldProduct){
        if (soldProduct == null) return null;
        return new SoldProductResponse(
                soldProduct.getId(),
                soldProduct.getQuantityTaken(),
                soldProduct.getTotalPrice(),
                Product.toProductResponse(soldProduct.getProduct())
        );
    }
}
