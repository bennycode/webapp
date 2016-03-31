package com.welovecoding.api.v1.category;

import com.welovecoding.api.v1.base.Logged;
import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import com.welovecoding.data.category.entity.Category;
import com.welovecoding.data.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.welovecoding.api.v1.category.CategoryMapper.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryResource {

    private final CategoryService categoryService;

    @Autowired
    CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO create(@PathVariable String categorySlug, @RequestBody CategoryDTO dto) {
        Category entity = dtoToEntity(dto);
        Category createdEntity = categoryService.save(entity);
        return entityToDto(createdEntity, 2);
    }

    @RequestMapping(value = "/{categorySlug}", method = RequestMethod.GET)
    public CategoryDTO read(@PathVariable String categorySlug) {
        System.out.println("TEST: " + categorySlug);
        return entityToDto(categoryService.findBySlug(categorySlug), 2);
    }

    @RequestMapping(value = "/{categorySlug}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO update(@PathVariable String categorySlug, @RequestBody CategoryDTO dto)
            throws NoEntityToUpdateFoundException {

        if (!dto.getSlug().equals(categorySlug)) {
            throw new NoEntityToUpdateFoundException("Slug does not match.");
        }

        Category entity = dtoToEntity(dto);
        Category updatedEntity = categoryService.update(entity);

        return entityToDto(updatedEntity, 2);
    }

    @RequestMapping(value = "/{categorySlug}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String categorySlug)
            throws NoEntityToDeleteFoundException {
        categoryService.delete(categorySlug);
    }

    @Logged
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<CategoryDTO> findAllCategories(
            @RequestParam(value = "direction", required = false) String direction,
            @RequestParam(value = "attribute", required = false) String attribute) {

        Sort.Direction defaultDirection = Sort.Direction.ASC;
        String defaultAttribute = "id";

        if (Sort.Direction.fromStringOrNull(direction) != null) {
            defaultDirection = Sort.Direction.fromStringOrNull(direction);
        }

        if (attribute != null) {
            defaultAttribute = attribute;
        }

        Page<Category> allAndSortBy = categoryService.findAllAndSortBy(defaultDirection, defaultAttribute);
        if (allAndSortBy != null) {
            return entityListToDtoList(allAndSortBy.getContent(), 2);
        } else {
            return new ArrayList<>();
        }
    }

}
