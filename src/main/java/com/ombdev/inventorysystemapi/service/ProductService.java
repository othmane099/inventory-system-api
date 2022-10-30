package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Product;
import com.ombdev.inventorysystemapi.model.SortBy;
import com.ombdev.inventorysystemapi.repository.ProductRepository;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.product.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public Page<ProductResponse> index(String keyword, int page, int size, SortBy sortBy){

        Page<Product> productsPage;

        switch (sortBy){
            case PRODUCT_CODE_ASC -> productsPage = productRepository
                    .findAllByProductCodeContainingOrProductNameContainingOrderByProductCodeAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case PRODUCT_CODE_DESC -> productsPage = productRepository
                    .findAllByProductCodeContainingOrProductNameContainingOrderByProductCodeDesc
                            (keyword, keyword, PageRequest.of(page, size));
            case PRODUCT_NAME_ASC -> productsPage = productRepository
                    .findAllByProductCodeContainingOrProductNameContainingOrderByProductNameAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case PRODUCT_NAME_DESC -> productsPage = productRepository
                    .findAllByProductCodeContainingOrProductNameContainingOrderByProductNameDesc
                            (keyword, keyword, PageRequest.of(page, size));
            default ->  productsPage = productRepository
                    .findAllByProductCodeContainingOrProductNameContainingOrderByIdDesc
                            (keyword, keyword, PageRequest.of(page, size));
        }

        List<ProductResponse> products = productsPage
                .stream()
                .map(Product::toProductResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(products, PageRequest.of(page, size), productsPage.getTotalElements());

    }

    public ProductResponse store(Product product){
        return Product.toProductResponse(productRepository.save(product));
    }

    public ProductResponse show(Long id){
        Product product = productRepository.findById(id).get();
        return Product.toProductResponse(product);
    }

    public ProductResponse update(Product product) {
        return Product.toProductResponse(productRepository.save(product));
    }

    public DeleteResponse destroy(Long id) {
        productRepository.deleteById(id);
        return new DeleteResponse("Product deleted successfully :)");
    }

    public DeleteResponse destroyAll(List<Long> products){
        productRepository.deleteAllById(products);
        return new DeleteResponse("Selected products deleted successfully :)");
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(Product::toProductResponse)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getAllByKeyword(String keyword) {
        return productRepository.findAllByProductCodeContainingOrProductNameContaining(keyword, keyword)
                .stream()
                .map(Product::toProductResponse)
                .collect(Collectors.toList());
    }
}
