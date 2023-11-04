package com.crcl.iam.validation.validators;

import com.crcl.iam.repository.UserRepository;
import com.crcl.iam.validation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Object> {

    private final UserRepository userRepository;

    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(Object email, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userRepository.existsByEmailIgnoreCase(String.valueOf(email));
    }
}
