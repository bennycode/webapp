package com.welovecoding.data.base;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntityValidator {

  private static final Logger LOG = Logger.getLogger(EntityValidator.class.getName());

  public static void validateEntity(BaseEntity entity) throws ConstraintViolationBagException {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<BaseEntity>> constraintViolations = validator.validate(entity);

    Iterator<ConstraintViolation<BaseEntity>> iterator = constraintViolations.iterator();
    while (iterator.hasNext()) {
      ConstraintViolation<BaseEntity> item = iterator.next();
      String property = item.getPropertyPath().toString();
      LOG.log(Level.INFO, "Validation error in ''{0}''. Reason: ''{1}''.", new Object[]{property, item.getMessage()});
    }

    if (constraintViolations.size() > 0) {
      throw new ConstraintViolationBagException(constraintViolations);
    }
  }
}
