package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Category;
import com.ombdev.inventorysystemapi.repository.CategoryRepository;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.category.CategoryRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.category.CategoryResponse;
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

    public CategoryResponse create(Category category){
        return Category.toCategoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse show(Long id){
        Category category = categoryRepository.findById(id).get();
        return Category.toCategoryResponse(category);
    }

    public CategoryResponse update(Category category){
        return Category.toCategoryResponse(categoryRepository.save(category));
    }

    public DeleteResponse delete(Long id){
        categoryRepository.deleteById(id);
        return new DeleteResponse("Category deleted successfully :)");
    }
}
