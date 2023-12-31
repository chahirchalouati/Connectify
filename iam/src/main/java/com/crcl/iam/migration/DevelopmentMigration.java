package com.crcl.iam.migration;

import com.crcl.iam.configuration.props.DevelopProperties;
import com.crcl.iam.configuration.props.Registration;
import com.crcl.iam.domain.Client;
import com.crcl.iam.domain.User;
import com.crcl.iam.helpers.MigrationProvider;
import com.crcl.iam.repository.UserRepository;
import com.crcl.iam.utils.GramifyClientScopes;
import com.crcl.iam.utils.RoleUtils;
import com.crcl.iam.utils.UserGenerator;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_POST;

@Profile({"dev", "docker"})
@Slf4j
@ChangeLog
public class DevelopmentMigration {

    @ChangeSet(order = "001", id = "save_default_clients", author = "@chahir_chalouati")
    public void saveClients(MigrationProvider migrationProvider) {
        migrationProvider.getSecurityProperties().getRegistrations().values()
                .stream()
                .map(registration -> buildClient(migrationProvider, registration))
                .forEach(migrationProvider.getClientRepository()::save);
    }

    @ChangeSet(order = "002", id = "save_system_clients", author = "@chahir_chalouati")
    public void saveSystemClient(MigrationProvider migrationProvider) {
        Client client = new Client()
                .setId("SYSTEM")
                .setClientId("SYSTEM")
                .setClientSecret(migrationProvider.getPasswordEncoder().encode("SYSTEM"))
                .setClientAuthenticationMethods(Set.of(CLIENT_SECRET_POST))
                .setAuthorizationGrantTypes(Set.of(AuthorizationGrantType.CLIENT_CREDENTIALS))
                .setRedirectUris(Collections.emptySet())
                .setScopes(GramifyClientScopes.UI_SCOPES);
        migrationProvider.getClientRepository().save(client);
    }

    @ChangeSet(order = "003", id = "save_dummy_users", author = "@chahir_chalouati")
    public void saveDummyUsers(MigrationProvider migrationProvider) {
        UserRepository userRepository = migrationProvider.getUserRepository();
        if (userRepository.count() <= 1) {
            log.info("Bootstrapping users for development environment");
            final DevelopProperties properties = migrationProvider.getDevelopProperties();
            final PasswordEncoder passwordEncoder = migrationProvider.getPasswordEncoder();
            final String encodedPassword = passwordEncoder.encode(properties.getPassword());
            final List<User> users = UserGenerator.generateRandomUsers(
                            properties.getCount(),
                            properties.getUsername(),
                            encodedPassword)
                    .stream()
                    .map(this::addDefaultRoles)
                    .peek(user -> log.info("Created new user: {}", user.getUsername()))
                    .toList();
            userRepository.saveAll(users);
            log.info("Finished bootstrapping users for development environment");
        }
    }

    private User addDefaultRoles(User user) {
        return user.setRoles(RoleUtils.getDefaultUserRoles());
    }

    private Client buildClient(MigrationProvider migrationProvider, Registration registration) {
        registration.getUris().add("https://oauth.pstmn.io/v1/callback");
        return ClientMigration.getClient(migrationProvider, registration);
    }
}

