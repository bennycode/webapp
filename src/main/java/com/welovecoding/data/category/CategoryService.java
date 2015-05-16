package com.welovecoding.data.category;

import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.welovecoding.ParameterValidator.isNull;
import static com.welovecoding.ParameterValidator.notNull;
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

  public Category findOne(Long id) {
    notNull(id, new IllegalArgumentException("ID is null"));
    return getRepository().findOne(id);
  }

  public List<Category> findAll() {
    return getRepository().findAll();
  }

  public Category save(Category entity) {
    notNull(entity, new IllegalArgumentException("Entity is null"));
    isNull(entity.getId(), new IllegalArgumentException("ID of entity is set"));
    validateEntity(entity);
    return getRepository().save(entity);
  }

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

  public void delete(Long id) throws NoEntityToDeleteFoundException {
    notNull(id, new IllegalArgumentException("ID is null"));
    if (!getRepository().exists(id)) {
      throw new NoEntityToDeleteFoundException("Can't find the entity to delete");
    } else {
      getRepository().delete(id);
    }
  }

  public Set<Category> findAllOrderedByName() {
    return getRepository().findAllOrderedByName();
  }

  public Category findBySlug(String slug) {
    return getRepository().findBySlug(slug);
  }

}
