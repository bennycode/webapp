package com.welovecoding.view;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsNotEmptyValidator.class)
@Documented
public @interface PasswordsNotEmpty {

  String message() default "PasswordsNotEmpty";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String triggerFieldName() default "";

  String passwordFieldName() default "";

  String passwordVerificationFieldName() default "";
}
