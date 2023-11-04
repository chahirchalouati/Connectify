package com.crcl.iam.helpers;

import com.crcl.iam.configuration.props.DevelopProperties;
import com.crcl.iam.configuration.props.SecurityProperties;
import com.crcl.iam.repository.MongoClientRepository;
import com.crcl.iam.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface MigrationProvider {
    SecurityProperties getSecurityProperties();

    MongoClientRepository getClientRepository();

    PasswordEncoder getPasswordEncoder();

    UserRepository getUserRepository();

    DevelopProperties getDevelopProperties();
}
