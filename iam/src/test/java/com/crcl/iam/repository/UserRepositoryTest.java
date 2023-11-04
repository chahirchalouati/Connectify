package com.crcl.iam.repository;

import com.crcl.iam.domain.Gender;
import com.crcl.iam.domain.User;
import com.crcl.iam.utils.assertions.UserAssertion;
import com.crcl.iam.utils.builders.UserTestBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class UserRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void saveUsers() {
        User user = UserTestBuilder.createUser()
                .withUsername("username")
                .withGender(Gender.MALE)
                .build();

        User save = userRepository.save(user);

        UserAssertion.assertThat(save)
                .hasUsername("username")
                .hasId();

    }


}