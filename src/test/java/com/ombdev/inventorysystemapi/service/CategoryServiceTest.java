package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.exception.InventorySystemException;
import com.ombdev.inventorysystemapi.model.Category;
import com.ombdev.inventorysystemapi.model.SortBy;
import com.ombdev.inventorysystemapi.repository.CategoryRepository;
import com.ombdev.inventorysystemapi.response.category.CategoryResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class CategoryServiceTest {


    @Autowired
    private  CategoryRepository categoryRepository;
    @Autowired
    private  CategoryService categoryService;

    private final String CategoryCodeTest = "CategoryCodeTest";
    private final String CategoryNameTest = "CategoryNameTest";

    @BeforeEach
    public void setup(){
        Category cat = new Category();
        cat.setCategoryCode(CategoryCodeTest);
        cat.setCategoryName(CategoryNameTest);
        categoryRepository.save(cat);
    }

    @Test
    public void index() {
        String keyword = "";
        int page = 0;
        int size = 10;
        SortBy sortBy = SortBy.ID_DESC;
        Page<CategoryResponse> categories = categoryService.index(keyword, page, size, sortBy);
        CategoryResponse myCategory = categories.getContent().get(0);

        Assertions.assertEquals(1, categories.getTotalElements(), "Must have one element in result");
        Assertions.assertEquals(CategoryCodeTest, myCategory.categoryCode());
        Assertions.assertEquals(CategoryNameTest, myCategory.categoryName());
    }

    @Test
    public void getAll() {
        List<CategoryResponse> categories = categoryService.getAll();
        CategoryResponse myCategory = categories.get(0);

        Assertions.assertEquals(1, categories.size(), "Must have one element in result");
        Assertions.assertEquals(CategoryCodeTest, myCategory.categoryCode());
        Assertions.assertEquals(CategoryNameTest, myCategory.categoryName());
    }

    @Test
    public void showWithNullId() {
        Assertions.assertThrows(InventorySystemException.class,
                () -> { categoryService.show(null); },
                "Should throw exception");
    }

    @Test
    public void show() {
        Category categoryTest = categoryRepository.findByCategoryCode(CategoryCodeTest).get();
        CategoryResponse category = categoryService.show(categoryTest.getId());
        Assertions.assertEquals(CategoryCodeTest, category.categoryCode(), "should equal CategoryCodeTest");
    }

    @Test
    public void update() {
        Category categoryTest = categoryRepository.findByCategoryCode(CategoryCodeTest).get();
        categoryTest.setCategoryName("CategoryNameTestUpdated");
        CategoryResponse category = categoryService.update(categoryTest);

        Assertions.assertEquals("CategoryNameTestUpdated", category.categoryName(), "should equal CategoryCodeTest");
    }

    @Test
    public void destroy() {
        Category cat = new Category();
        cat.setCategoryCode("destroyCategoryCode");
        cat.setCategoryName("destroyCategoryName");
        categoryRepository.save(cat);

        Optional<Category> categoryTest = categoryRepository.findByCategoryCode("destroyCategoryCode");
        Assertions.assertTrue(categoryTest.isPresent());

        categoryService.destroy(categoryTest.get().getId());
        Optional<Category> category = categoryRepository.findByCategoryCode("destroyCategoryCode");

        Assertions.assertFalse(category.isPresent(), "should equal False");
    }

    @AfterEach
    public void end(){
        categoryRepository.delete(categoryRepository.findByCategoryCode(CategoryCodeTest).get());
    }
}
