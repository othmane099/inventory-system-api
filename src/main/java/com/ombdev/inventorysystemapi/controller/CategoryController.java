package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.category.CreateCategoryRequest;
import com.ombdev.inventorysystemapi.request.category.UpdateCategoryRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.category.CreateCategoryResponse;
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
    public CreateCategoryResponse create(@RequestBody CreateCategoryRequest request){
        return categoryService.create(request);
    }

    @GetMapping("/special/categories/show")
    public CategoryResponse show(ShowRequest request){
        return categoryService.show(request);
    }

    @DeleteMapping("/special/categories/delete")
    public DeleteResponse delete(@RequestBody DeleteRequest request){
        return categoryService.delete(request);
    }

    @PutMapping("/special/categories/update")
    public CategoryResponse update(@RequestBody UpdateCategoryRequest request){
        return categoryService.update(request);
    }
}
