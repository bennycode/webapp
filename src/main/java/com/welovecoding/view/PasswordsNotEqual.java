package com.welovecoding.view;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsNotEqualValidator.class)
@Documented
public @interface PasswordsNotEqual {

  String message() default "PasswordsNotEqual";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String passwordFieldName() default "";

  String passwordVerificationFieldName() default "";
}
