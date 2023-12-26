package com.crcl.iam.aspects;

import com.crcl.iam.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class UniqueUserNameAspect {

    private final UserRepository userRepository;

    @AfterReturning(pointcut = "@annotation(com.crcl.iam.validators.annotations.UniqueUserName)", returning = "result")
    public void checkUniqueUserName(Object result) {
        if (result instanceof String username) {
            userRepository.findByUserName(username)
                    .doOnNext(existingUser -> {
                        if (existingUser != null) {
                            throw new NonUniqueUserNameException("Username is not unique");
                        }
                    })
                    .subscribe();
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    private static class NonUniqueUserNameException extends RuntimeException {
        public NonUniqueUserNameException(String usernameIsNotUnique) {
        }
    }
}
