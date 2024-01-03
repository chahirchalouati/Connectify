package com.crcl.iam.validators.annotations.validators;

import com.crcl.iam.repositories.PermissionRepository;
import com.crcl.iam.validators.annotations.UniquePermission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UniquePermissionValidator implements ConstraintValidator<UniquePermission, String> {

    private final PermissionRepository permissionRepository;
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !permissionRepository.existsByNameIgnoreCase(name);
    }
}
