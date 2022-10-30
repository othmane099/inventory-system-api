package com.ombdev.inventorysystemapi.request.sale;

import com.ombdev.inventorysystemapi.model.PaymentMethod;
import com.ombdev.inventorysystemapi.model.Sale;
import com.ombdev.inventorysystemapi.request.customer.CustomerRequest;
import com.ombdev.inventorysystemapi.request.product.ProductRequest;

import java.util.Set;
import java.util.stream.Collectors;

public record SaleRequest(
        Long id, String saleCode, Double totalPrice, PaymentMethod paymentMethod,
        Set<ProductRequest> products, CustomerRequest customer

) {

    public static Sale toEntity(SaleRequest request){
        if (request == null) return null;
        Sale sale = new Sale();
        sale.setId(request.id());
        sale.setSaleCode(request.saleCode());
        sale.setTotalPrice(request.totalPrice());
        sale.setPaymentMethod(request.paymentMethod());
        sale.setCustomer(CustomerRequest.toEntity(request.customer()));
        sale.setProducts(request.products() != null ?
                request.products().stream()
                        .map(ProductRequest::toEntity)
                        .collect(Collectors.toSet()) : null);
        return sale;
    }
}
