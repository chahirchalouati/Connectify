package com.crcl.iam.migration;

import com.crcl.iam.domain.Role;
import com.crcl.iam.repository.RoleRepository;
import com.crcl.iam.utils.DefaultRoles;
import com.crcl.iam.utils.RoleUtils;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

@ChangeLog(order = "1")
public class RolesSetupChangeLog {

    @ChangeSet(order = "001", id = "add_role_admin", author = "@chahir_chalouati")
    public void createRoleAdmin(RoleRepository roleRepository) {
        Role role = new Role(DefaultRoles.ROLE_ADMIN);
        roleRepository.insert(role);
    }

    @ChangeSet(order = "003", id = "add_roles_user", author = "@chahir_chalouati")
    public void createRoles(RoleRepository roleRepository) {
        roleRepository.insert(RoleUtils.getDefaultUserRoles());
    }
}
