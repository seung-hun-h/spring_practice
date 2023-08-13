package com.shcorp.voucher.voucher.domain;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public abstract class SelfValidating<T> {

  private final Validator validator;

  protected SelfValidating() {
    try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      validator = factory.getValidator();
    }
  }

  /**
   * Evaluates all Bean Validations on the attributes of this
   * instance.
   */
  @SuppressWarnings("unchecked")
  protected void validateSelf() {
    Set<ConstraintViolation<T>> violations = validator.validate((T) this);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
