package com.welovecoding.data.tutorial.service;

import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import com.welovecoding.data.tutorial.entity.Author;
import com.welovecoding.data.tutorial.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.welovecoding.ParameterValidator.isNull;
import static com.welovecoding.ParameterValidator.notNull;
import com.welovecoding.api.v1.base.Logged;
import static com.welovecoding.data.base.EntityValidator.validateEntity;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    private AuthorRepository getRepository() {
        return authorRepository;
    }

    @Logged
    public Author findOne(Long id) {
        notNull(id, new IllegalArgumentException("ID is null"));
        return getRepository().findOne(id);
    }

    @Logged
    public List<Author> findAll() {
        return getRepository().findAll();
    }

    @Logged
    public Author save(Author entity) {
        notNull(entity, new IllegalArgumentException("Entity is null"));
        isNull(entity.getId(), new IllegalArgumentException("ID of entity is set"));
        validateEntity(entity);
        return getRepository().save(entity);
    }

    @Logged
    public Author update(Author entity) throws NoEntityToUpdateFoundException {
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
    public Author findBySlug(String slug) {
        return getRepository().findBySlug(slug);
    }

}
