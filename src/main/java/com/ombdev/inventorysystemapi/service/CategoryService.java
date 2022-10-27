package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Category;
import com.ombdev.inventorysystemapi.model.SortBy;
import com.ombdev.inventorysystemapi.repository.CategoryRepository;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.category.CategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public Page<CategoryResponse> index(String keyword, int page, int size, SortBy sortBy){
        Page<Category> categoriesPage;

        switch (sortBy) {
            case CATEGORY_CODE_ASC -> categoriesPage = categoryRepository
                    .findAllByCategoryCodeContainingOrCategoryNameContainingOrderByCategoryCodeAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case CATEGORY_CODE_DESC -> categoriesPage = categoryRepository
                    .findAllByCategoryCodeContainingOrCategoryNameContainingOrderByCategoryCodeDesc
                    (keyword, keyword, PageRequest.of(page, size));
            case CATEGORY_NAME_ASC -> categoriesPage = categoryRepository
                    .findAllByCategoryCodeContainingOrCategoryNameContainingOrderByCategoryNameAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case CATEGORY_NAME_DESC -> categoriesPage = categoryRepository
                    .findAllByCategoryCodeContainingOrCategoryNameContainingOrderByCategoryNameDesc
                            (keyword, keyword, PageRequest.of(page, size));
            default -> categoriesPage = categoryRepository
                    .findAllByCategoryCodeContainingOrCategoryNameContainingOrderByIdDesc
                            (keyword, keyword, PageRequest.of(page, size));
        }
        List<CategoryResponse> categories = categoriesPage
                .stream()
                .map(Category::toCategoryResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(categories, PageRequest.of(page, size), categoriesPage.getTotalElements());

    }

    public CategoryResponse store(Category category){
        return Category.toCategoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse show(Long id){
        Category category = categoryRepository.findById(id).get();
        return Category.toCategoryResponse(category);
    }

    public CategoryResponse update(Category category){
        return Category.toCategoryResponse(categoryRepository.save(category));
    }

    public DeleteResponse destroy(Long id){
        categoryRepository.deleteById(id);
        return new DeleteResponse("Category deleted successfully :)");
    }

    public DeleteResponse destroyAll(List<Long> categories){
        categoryRepository.deleteAllById(categories);
        return new DeleteResponse("Selected categories deleted successfully :)");
    }

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(Category::toCategoryResponse)
                .collect(Collectors.toList());
    }
}
