package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.category.CategoryRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.category.CategoryResponse;
import com.ombdev.inventorysystemapi.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/special/categories")
    public List<CategoryResponse> index(){
        return categoryService.index();
    }

    @PostMapping("/special/categories/create")
    public CategoryResponse create(@RequestBody CategoryRequest request){
        return categoryService.create(CategoryRequest.toEntity(request));
    }

    @GetMapping("/special/categories/show")
    public CategoryResponse show(@RequestBody ShowRequest request){
        return categoryService.show(request.id());
    }

    @DeleteMapping("/special/categories/delete")
    public DeleteResponse delete(@RequestBody DeleteRequest request){
        return categoryService.delete(request.id());
    }

    @PutMapping("/special/categories/update")
    public CategoryResponse update(@RequestBody CategoryRequest request){
        return categoryService.update(CategoryRequest.toEntity(request));
    }
}
