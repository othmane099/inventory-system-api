package com.ombdev.inventorysystemapi.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ombdev.inventorysystemapi.model.Category;
import com.ombdev.inventorysystemapi.repository.CategoryRepository;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.category.CategoryRequest;
import com.ombdev.inventorysystemapi.service.CategoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

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
    public void allCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/special/categories/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void index() throws Exception {
        Category cat2 = new Category();
        cat2.setCategoryCode("CategoryCodeTest2");
        cat2.setCategoryName("CategoryNameTest2");
        categoryRepository.save(cat2);

        Optional<Category> categoryTest = categoryRepository.findByCategoryCode("CategoryCodeTest2");
        Assertions.assertTrue(categoryTest.isPresent());

        mockMvc.perform(MockMvcRequestBuilders.get("/special/categories?keyword=C&page=0&size=10&sortBy=ID_DESC"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(2)));

        categoryService.destroy(cat2.getId());
    }


    @Test
    public void store() throws Exception {
        Category category = new Category();
        category.setCategoryCode("newCategoryCode2");
        category.setCategoryName("newCategoryName2");

        mockMvc.perform(MockMvcRequestBuilders.post("/special/categories/store")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryCode", Matchers.equalTo("newCategoryCode2")));

        Category category2 = categoryRepository.findByCategoryCode("newCategoryCode2").get();
        Assertions.assertEquals("newCategoryName2",
                category2.getCategoryName(),
                "Student should be valid.");
    }

    @Test
    public void update() throws Exception {
        Category category = new Category();
        category.setCategoryCode("updateCategoryCode");
        category.setCategoryName("updateCategoryName");
        categoryService.store(category);

        Category updatedCategory = categoryRepository.findByCategoryCode("updateCategoryCode").get();

        CategoryRequest request = new CategoryRequest(updatedCategory.getId(),
                updatedCategory.getCategoryCode(),
                "updateCategoryName99");

        mockMvc.perform(MockMvcRequestBuilders.put("/special/categories/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName", Matchers.equalTo("updateCategoryName99")));

        Category category2 = categoryRepository.findByCategoryCode("updateCategoryCode").get();
        Assertions.assertEquals("updateCategoryName99",
                category2.getCategoryName(),
                "Student should be valid.");
    }

    @Test
    public void destroy() throws Exception {
        Category cat = new Category();
        cat.setCategoryCode("destroyCategoryCode");
        cat.setCategoryName("destroyCategoryName");
        categoryRepository.save(cat);

        Optional<Category> categoryTest = categoryRepository.findByCategoryCode("destroyCategoryCode");
        Assertions.assertTrue(categoryTest.isPresent());
        DeleteRequest request = new DeleteRequest(categoryTest.get().getId());
        mockMvc.perform(MockMvcRequestBuilders.delete("/special/categories/destroy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message", Matchers.equalTo("Category deleted successfully :)")));

        Assertions.assertFalse(categoryRepository.findByCategoryCode(categoryTest.get().getCategoryCode()).isPresent());
    }

    @Test
    public void destroyAll() throws Exception {
        Category cat = new Category();
        cat.setCategoryCode("destroyAllCategoryCode1");
        cat.setCategoryName("destroyAllCategoryName1");
        categoryService.store(cat);

        Category cat2 = new Category();
        cat2.setCategoryCode("destroyAllCategoryCode2");
        cat2.setCategoryName("destroyAllCategoryName2");
        categoryService.store(cat2);

        Optional<Category> categoryTest1 = categoryRepository.findByCategoryCode(cat.getCategoryCode());
        Assertions.assertTrue(categoryTest1.isPresent());
        Optional<Category> categoryTest2 = categoryRepository.findByCategoryCode(cat2.getCategoryCode());
        Assertions.assertTrue(categoryTest2.isPresent());

        List<DeleteRequest> requestDelete = List.of(new DeleteRequest(cat.getId()), new DeleteRequest(cat2.getId()));
        mockMvc.perform(MockMvcRequestBuilders.delete("/special/categories/destroyAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDelete)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message", Matchers.equalTo("Selected categories deleted successfully :)")));

        Assertions.assertFalse(categoryRepository.findByCategoryCode(cat.getCategoryCode()).isPresent());
        Assertions.assertFalse(categoryRepository.findByCategoryCode(cat2.getCategoryCode()).isPresent());
    }

    @AfterEach
    public void end(){
        categoryRepository.delete(categoryRepository.findByCategoryCode(CategoryCodeTest).get());
    }
}
