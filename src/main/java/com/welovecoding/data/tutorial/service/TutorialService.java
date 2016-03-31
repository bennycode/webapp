package com.welovecoding.data.tutorial.service;

import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import com.welovecoding.data.tutorial.entity.Tutorial;
import com.welovecoding.data.tutorial.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.welovecoding.ParameterValidator.isNull;
import static com.welovecoding.ParameterValidator.notNull;
import com.welovecoding.api.v1.base.Logged;
import static com.welovecoding.data.base.EntityValidator.validateEntity;

@Service
public class TutorialService {

    private final TutorialRepository tutorialRepository;

    @Autowired
    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    private TutorialRepository getRepository() {
        return tutorialRepository;
    }

    @Logged
    public Tutorial findOne(Long id) {
        notNull(id, new IllegalArgumentException("ID is null"));
        return getRepository().findOne(id);
    }

    @Logged
    public List<Tutorial> findAll() {
        return getRepository().findAll();
    }

    @Logged
    public Tutorial save(Tutorial entity) {
        notNull(entity, new IllegalArgumentException("Entity is null"));
        isNull(entity.getId(), new IllegalArgumentException("ID of entity is set"));
        validateEntity(entity);
        return getRepository().save(entity);
    }

    @Logged
    public Tutorial update(Tutorial entity) throws NoEntityToUpdateFoundException {
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
    public Tutorial findBySlug(String slug) {
        return getRepository().findBySlug(slug);
    }

}
