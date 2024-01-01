package com.crcl.iam.validators.annotations.validators;

import com.crcl.iam.repositories.UserRepository;
import com.crcl.iam.validators.annotations.UniqueUserName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(userRepository.findByUserName(userName).block());
    }
}
