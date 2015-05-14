package com.welovecoding.data.base;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

public class ConstraintViolationBagException extends RuntimeException {

  private final Set<ConstraintViolation<?>> constraintViolations;

  public ConstraintViolationBagException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
    super(message);
    this.constraintViolations = new HashSet<>(constraintViolations);
  }

  public ConstraintViolationBagException(Set<? extends ConstraintViolation<?>> constraintViolations) {
    this.constraintViolations = new HashSet<>(constraintViolations);
  }

  public Set<ConstraintViolation<?>> getConstraintViolations() {
    return constraintViolations;
  }
}
