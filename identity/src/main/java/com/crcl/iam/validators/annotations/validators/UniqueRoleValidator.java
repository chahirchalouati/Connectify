package com.crcl.iam.validators.annotations.validators;

import com.crcl.iam.repositories.RoleRepository;
import com.crcl.iam.validators.annotations.UniqueRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UniqueRoleValidator implements ConstraintValidator<UniqueRole, String> {

    private final RoleRepository roleRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !roleRepository.existsByNameIgnoreCase(name);
    }
}
