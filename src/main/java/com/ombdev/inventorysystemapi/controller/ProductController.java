package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.model.SortBy;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.product.ProductRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.product.ProductResponse;
import com.ombdev.inventorysystemapi.service.ProductService;
import com.ombdev.inventorysystemapi.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(Constants.CLIENT_BASE_URL)
public class ProductController {

    private final ProductService productService;

    @GetMapping("/special/products")
    public Page<ProductResponse> index(@RequestParam String keyword,
                                       @RequestParam int page,
                                       @RequestParam int size,
                                       @RequestParam SortBy sortBy){
        return productService.index(keyword, page, size, sortBy);
    }

    @PostMapping("/special/products/create")
    public ProductResponse store(@RequestBody ProductRequest request){
        return productService.store(ProductRequest.toEntity(request));
    }

    @GetMapping("/special/products/show")
    public ProductResponse show(@RequestBody ShowRequest request){
        return productService.show(request.id());
    }

    @DeleteMapping("/special/products/delete")
    public DeleteResponse delete(@RequestBody DeleteRequest request){
        return productService.destroy(request.id());
    }

    @DeleteMapping("/special/products/destroyAll")
    public DeleteResponse destroyAll(@RequestBody List<DeleteRequest> request){
        return productService.destroyAll(request
                .stream()
                .map(DeleteRequest::getIds)
                .collect(Collectors.toList()));
    }

    @PutMapping("/special/products/update")
    public ProductResponse update(@RequestBody ProductRequest request){
        return productService.update(ProductRequest.toEntity(request));
    }
}
