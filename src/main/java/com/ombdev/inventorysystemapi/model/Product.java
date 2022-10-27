package com.ombdev.inventorysystemapi.model;

import com.ombdev.inventorysystemapi.response.product.ProductResponse;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productCode;
    private String productName;
    private String photo;
    private Integer quantity;
    private Double buyingPrice;
    private Double sellingPrice;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new TreeSet<>();

    @ManyToMany(mappedBy = "products")
    private Set<Sale> sales = new HashSet<>();

    public static ProductResponse toProductResponse(Product product){
        if (product == null) return null;
        return new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getProductCode(),
                product.getCategories() != null ?
                        product.getCategories().stream()
                                .map(Category::toCategoryResponse)
                                .collect(Collectors.toSet()) : null,
                product.getSellingPrice(),
                product.getBuyingPrice(),
                product.getQuantity()
        );
    }


}
