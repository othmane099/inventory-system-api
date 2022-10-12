package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Category;
import com.ombdev.inventorysystemapi.repository.CategoryRepository;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.category.CreateCategoryRequest;
import com.ombdev.inventorysystemapi.request.category.UpdateCategoryRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.category.CategoryResponse;
import com.ombdev.inventorysystemapi.response.category.CreateCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> index(){
        return categoryRepository.findAll()
                .stream()
                .map(Category::toCategoryResponse)
                .collect(Collectors.toList());

    }

    public CreateCategoryResponse create(CreateCategoryRequest request){
        Category category = CreateCategoryRequest.toEntity(request);
        return Category.toCreateCategoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse show(ShowRequest request){
        Category category = categoryRepository.findById(request.id()).get();
        return Category.toCategoryResponse(category);
    }

    public CategoryResponse update(UpdateCategoryRequest request){
        Category category = UpdateCategoryRequest.toEntity(request);
        return Category.toCategoryResponse(categoryRepository.save(category));
    }

    public DeleteResponse delete(DeleteRequest request){
        categoryRepository.deleteById(request.id());
        return new DeleteResponse("Category deleted successfully :)");
    }
}
