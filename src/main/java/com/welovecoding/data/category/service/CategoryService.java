package com.welovecoding.data.category.service;

import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import com.welovecoding.data.category.entity.Category;
import com.welovecoding.data.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.welovecoding.ParameterValidator.isNull;
import static com.welovecoding.ParameterValidator.notEmpty;
import static com.welovecoding.ParameterValidator.notNull;
import com.welovecoding.api.v1.base.Logged;
import static com.welovecoding.data.base.EntityValidator.validateEntity;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private CategoryRepository getRepository() {
        return categoryRepository;
    }

    @Logged
    public Category findOne(Long id) {
        notNull(id, new IllegalArgumentException("ID is null"));
        return getRepository().findOne(id);
    }

    @Logged
    public List<Category> findAll() {
        return getRepository().findAll();
    }

    @Logged
    public Category save(Category entity) {
        notNull(entity, new IllegalArgumentException("Entity is null"));
        isNull(entity.getId(), new IllegalArgumentException("ID of entity is set"));
        validateEntity(entity);
        return getRepository().save(entity);
    }

    @Logged
    public Category update(Category entity) throws NoEntityToUpdateFoundException {
        notNull(entity, new IllegalArgumentException("Entity is null"));
        notNull(entity.getId(), new IllegalArgumentException("ID is null"));
        validateEntity(entity);
        if (!getRepository().exists(entity.getId())) {
            throw new NoEntityToUpdateFoundException("Can't find the entity to update");
        } else {
            return getRepository().save(entity);
        }
    }

    @Logged
    public void delete(Long id) throws NoEntityToDeleteFoundException {
        notNull(id, new IllegalArgumentException("ID is null"));
        if (!getRepository().exists(id)) {
            throw new NoEntityToDeleteFoundException("Can't find the entity to delete");
        } else {
            getRepository().delete(id);
        }
    }

    @Logged
    public void delete(String categorySlug) throws NoEntityToDeleteFoundException {
        notNull(categorySlug, new IllegalArgumentException("Slug is null"));
        notEmpty(categorySlug, new IllegalArgumentException("Slug is empty"));
        Category entity = findBySlug(categorySlug);
        notNull(entity, new IllegalArgumentException("Entity is null"));
        delete(entity.getId());
    }

    @Logged
    public Category findBySlug(String slug) {
        return getRepository().findBySlug(slug);
    }

    @Logged
    public Page<Category> findAllAndSortBy(Sort.Direction direction, String attribute) {
        return getRepository().findAll(new PageRequest(0, 10, direction, attribute));
    }

}
