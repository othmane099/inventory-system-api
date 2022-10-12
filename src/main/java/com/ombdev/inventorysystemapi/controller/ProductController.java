package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.product.ProductRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.product.ProductResponse;
import com.ombdev.inventorysystemapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/special/products")
    public List<ProductResponse> index(){
        return productService.index();
    }

    @PostMapping("/special/products/create")
    public ProductResponse create(@RequestBody ProductRequest request){
        return productService.create(request);
    }

    @GetMapping("/special/products/show")
    public ProductResponse show(@RequestBody ShowRequest request){
        return productService.show(request);
    }

    @DeleteMapping("/special/products/delete")
    public DeleteResponse delete(@RequestBody DeleteRequest request){
        return productService.delete(request);
    }

    @PutMapping("/special/products/update")
    public ProductResponse update(@RequestBody ProductRequest request){
        return productService.update(request);
    }
}
