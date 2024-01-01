package com.crcl.iam.validators.annotations;

import com.crcl.iam.validators.annotations.validators.UniquePermissionValidator;
import com.crcl.iam.validators.annotations.validators.UniqueRoleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueRoleValidator.class})
public @interface UniqueRole {
    String message() default "Role name must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
