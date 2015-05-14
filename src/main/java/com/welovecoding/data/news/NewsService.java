package com.welovecoding.data.news;


import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static com.welovecoding.ParameterValidator.*;
import static com.welovecoding.data.base.EntityValidator.validateEntity;
import static com.welovecoding.data.base.Slugify.slugify;

@Service
public class NewsService {

  private static final Logger LOG = Logger.getLogger(NewsService.class.getName());

  private final NewsRepository newsRepository;

  @Autowired
  public NewsService(NewsRepository newsRepository) {
    this.newsRepository = newsRepository;
  }

  private NewsRepository getRepository() {
    return newsRepository;
  }

  public News update(News entity) throws NoEntityToUpdateFoundException {
    notNull(entity, new IllegalArgumentException("Entity is null"));
    notNull(entity.getId(), new IllegalArgumentException("ID of entity is null"));
    validateEntity(entity);
    News oldEntity = this.findOne(entity.getId());
    if (oldEntity == null) {
      throw new NoEntityToUpdateFoundException("Can't find the entity to update");
    }

    if (!oldEntity.getTitle().equals(entity.getTitle())) {
      entity.setSlug(slugify(entity.getTitle()));
    }
    return getRepository().save(entity);
  }

  public News findOne(Long id) {
    notNull(id, new IllegalArgumentException("ID is null"));
    return getRepository().findOne(id);
  }

  public News findOneBySlug(String slug) {
    notNull(slug, new IllegalArgumentException("Slug is null"));
    notEmpty(slug, new IllegalArgumentException("Slug is empty"));
    return getRepository().findOneBySlug(slug);
  }

  public List<News> findAll() {
    return getRepository().findAll();
  }

  public News save(News entity) {
    notNull(entity, new IllegalArgumentException("Entity is null"));
    isNull(entity.getId(), new IllegalArgumentException("ID of entity is set"));
    entity.setSlug(slugify(entity.getTitle()));
    validateEntity(entity);
    return getRepository().save(entity);
  }

  public void delete(Long id) throws NoEntityToDeleteFoundException {
    notNull(id, new IllegalArgumentException("ID is null"));
    if (!getRepository().exists(id)) {
      throw new NoEntityToDeleteFoundException("Can't find the entity to delete");
    } else {
      getRepository().delete(id);
    }
  }

  public void deleteBySlug(String slug) throws NoEntityToDeleteFoundException {
    notNull(slug, new IllegalArgumentException("Slug is null"));
    notEmpty(slug, new IllegalArgumentException("Slug is empty"));
    News entityToDelete = this.findOneBySlug(slug);
    if (entityToDelete == null) {
      throw new NoEntityToDeleteFoundException("Can't find the entity to delete");
    }
    getRepository().delete(entityToDelete.getId());
  }
}
