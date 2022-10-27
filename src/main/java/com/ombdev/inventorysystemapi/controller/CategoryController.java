package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.model.SortBy;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.category.CategoryRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.category.CategoryResponse;
import com.ombdev.inventorysystemapi.service.CategoryService;
import com.ombdev.inventorysystemapi.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(Constants.CLIENT_BASE_URL)
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/special/categories/all")
    public List<CategoryResponse> allCategories(){
        return categoryService.getAll();
    }

    @GetMapping("/special/categories")
    public Page<CategoryResponse> index(@RequestParam String keyword,
                                        @RequestParam int page,
                                        @RequestParam int size,
                                        @RequestParam SortBy sortBy){
        return categoryService.index(keyword, page, size, sortBy);
    }

    @PostMapping("/special/categories/store")
    public CategoryResponse store(@RequestBody CategoryRequest request){
        return categoryService.store(CategoryRequest.toEntity(request));
    }

    @GetMapping("/special/categories/show")
    public CategoryResponse show(@RequestBody ShowRequest request){
        return categoryService.show(request.id());
    }

    @DeleteMapping("/special/categories/destroy")
    public DeleteResponse destroy(@RequestBody DeleteRequest request){
        return categoryService.destroy(request.id());
    }

    @PutMapping("/special/categories/update")
    public CategoryResponse update(@RequestBody CategoryRequest request){
        return categoryService.update(CategoryRequest.toEntity(request));
    }

    @DeleteMapping("/special/categories/destroyAll")
    public DeleteResponse destroyAll(@RequestBody List<DeleteRequest> request){
        return categoryService.destroyAll(request
                .stream()
                .map(DeleteRequest::getIds)
                .collect(Collectors.toList()));
    }

}
