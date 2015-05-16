package com.welovecoding.api.v1.category;


import com.welovecoding.data.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.Set;

import static com.welovecoding.api.v1.category.CategoryMapper.entitySetToDtoSet;
import static com.welovecoding.api.v1.category.CategoryMapper.entityToDto;


@RestController
@RequestMapping("/api/v1/categories")
@Produces("application/json")
public class CategoryResource {

  private final CategoryService categoryService;

  @Autowired
  CategoryResource(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @RequestMapping(value = "/{categorySlug}", method = RequestMethod.GET)
  public CategoryDTO findCategoryBySlug(@PathVariable String categorySlug) {
    return entityToDto(categoryService.findBySlug(categorySlug), 2);
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public Set<CategoryDTO> findAllCategoriesOrderedByName() {
    return entitySetToDtoSet(categoryService.findAllOrderedByName(), 2);
  }
}
