package com.crcl.iam.validators.annotations;

import com.crcl.iam.validators.annotations.validators.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * This annotation can be applied to a field to ensure that its values are unique for email addresses.
 * It is intended to be used in conjunction with the {@link Email} annotation to validate the field's value as a valid email format.
 * The validation logic is implemented by the {@link UniqueEmailValidator} class.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueEmailValidator.class})
public @interface UniqueEmail {
    String message() default "Email address must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
