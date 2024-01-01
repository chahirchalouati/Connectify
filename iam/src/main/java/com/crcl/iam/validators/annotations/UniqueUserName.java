package com.crcl.iam.validators.annotations;

import com.crcl.iam.validators.annotations.validators.UniqueUserNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * This annotation can be used to mark a field as unique for usernames.
 * It should be used in conjunction with the {@link NotBlank} annotation to validate that the value is not blank.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueUserNameValidator.class})
public @interface UniqueUserName {
    String message() default "Username must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
