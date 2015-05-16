package com.welovecoding.data.base;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseService<E extends AbstractPersistable<PK>, PK extends Serializable, R extends BaseRepository<E, PK>> {

  private static final Logger LOG = Logger.getLogger(BaseService.class.getName());

  protected abstract R getRepository();

  public E findOne(PK id) {
    return getRepository().findOne(id);
  }

  public List<E> findAll() {
    return getRepository().findAll();
  }

  public E save(E entity) {
    validateEntity(entity);
    return getRepository().save(entity);
  }

  public E update(E entity) {
    validateEntity(entity);
    if (getRepository().exists(entity.getId())) {
      return getRepository().save(entity);
    }
    return null; //FIXME: check if we really want to return null here
  }

  public void delete(PK id) {
    getRepository().delete(id);
  }

  protected String slugify(String stringToSlugify) {
    return Slugify.slugify(stringToSlugify);
  }

  protected void validateEntity(E entity) throws ConstraintViolationBagException {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<E>> constraintViolations = validator.validate(entity);

    Iterator<ConstraintViolation<E>> iterator = constraintViolations.iterator();
    while (iterator.hasNext()) {
      ConstraintViolation<E> item = iterator.next();
      String property = item.getPropertyPath().toString();
      LOG.log(Level.INFO, "Validation error in ''{0}''. Reason: ''{1}''.", new Object[]{property, item.getMessage()});
    }

    if (constraintViolations.size() > 0) {
      throw new ConstraintViolationBagException(constraintViolations);
    }
  }
}
