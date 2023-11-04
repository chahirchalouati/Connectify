package com.crcl.iam.helpers;

import com.crcl.iam.configuration.props.DevelopProperties;
import com.crcl.iam.configuration.props.SecurityProperties;
import com.crcl.iam.repository.MongoClientRepository;
import com.crcl.iam.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
@Getter
public class MigrationProviderImpl implements MigrationProvider {
    private final SecurityProperties securityProperties;
    private final MongoClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DevelopProperties developProperties;
}
