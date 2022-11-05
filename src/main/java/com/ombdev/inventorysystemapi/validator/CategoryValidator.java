package com.ombdev.inventorysystemapi.validator;

import com.ombdev.inventorysystemapi.model.Category;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

  public static List<String> validate(Category category) {
    List<String> errors = new ArrayList<>();

    if (category == null) {
      errors.add("Category should not be null");
      errors.add("Category's Code should not be empty");
      errors.add("Category's Name should not be empty");

      return errors;
    }

    if (category.getCategoryCode() == null || !StringUtils.hasLength(category.getCategoryCode())){
      errors.add("Category's Code should not be empty");
    }

    if (category.getCategoryName() == null || !StringUtils.hasLength(category.getCategoryName())){
      errors.add("Category's Name should not be empty");
    }
    return errors;
  }

}
