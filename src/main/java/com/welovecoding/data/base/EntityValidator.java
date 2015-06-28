package com.welovecoding.data.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class EntityValidator {

  private static final Logger LOG = LoggerFactory.getLogger(EntityValidator.class.getName());

  public static void validateEntity(BaseEntity entity) throws ConstraintViolationBagException {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<BaseEntity>> constraintViolations = validator.validate(entity);

    for (ConstraintViolation<BaseEntity> item : constraintViolations) {
      String property = item.getPropertyPath().toString();
      LOG.info("Validation error in ''{0}''. Reason: ''{1}''.", new Object[]{property, item.getMessage()});
    }

    if (constraintViolations.size() > 0) {
      throw new ConstraintViolationBagException(constraintViolations);
    }
  }
}
