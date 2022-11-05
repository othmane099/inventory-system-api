package com.ombdev.inventorysystemapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ombdev.inventorysystemapi.model.Category;
import com.ombdev.inventorysystemapi.repository.CategoryRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoryServiceTest {

    private static MockHttpServletRequest request;

    @Autowired
    private  CategoryRepository categoryRepository;
    @Autowired
    private  CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;



    // Start Store

    @Test
    public void storeCategory() throws Exception {
        Category category = new Category();
        category.setCategoryCode("newCategoryCode2");
        category.setCategoryName("newCategoryName2");

        mockMvc.perform(MockMvcRequestBuilders.post("/special/categories/store")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category))).andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryCode", Matchers.equalTo("newCategoryCode2")));

        Category category2 = categoryRepository.findByCategoryCode("newCategoryCode2").get();
        Assertions.assertEquals("newCategoryName2",
                category2.getCategoryName(),
                "Student should be valid.");
    }
    // End Store
}
