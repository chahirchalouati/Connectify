package com.crcl.iam.configuration;

import com.crcl.iam.mappers.RoleMapper;
import com.crcl.iam.mappers.RoleMapperImpl;
import com.crcl.iam.mappers.UserMapper;
import com.crcl.iam.mappers.UserMapperImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MappersConfiguration {
    @Bean
    protected UserMapper userMapper(final RoleMapper roleMapper) {
        return new UserMapperImpl(roleMapper);
    }

    @Bean
    protected RoleMapper roleMapper() {
        return new RoleMapperImpl();
    }
}
