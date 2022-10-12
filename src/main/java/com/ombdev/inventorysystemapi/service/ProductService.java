package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Product;
import com.ombdev.inventorysystemapi.repository.ProductRepository;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.product.ProductRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.product.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> index(){
        return productRepository.findAll()
                .stream()
                .map(Product::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse create(ProductRequest request){
        Product product = ProductRequest.toEntity(request);
        return Product.toProductResponse(productRepository.save(product));
    }

    public ProductResponse show(ShowRequest request){
        Product product = productRepository.findById(request.id()).get();
        return Product.toProductResponse(product);
    }

    public ProductResponse update(ProductRequest request) {
        Product product = ProductRequest.toEntity(request);
        return Product.toProductResponse(productRepository.save(product));
    }

    public DeleteResponse delete(DeleteRequest request) {
        productRepository.deleteById(request.id());
        return new DeleteResponse("Product deleted successfully :)");
    }
}
