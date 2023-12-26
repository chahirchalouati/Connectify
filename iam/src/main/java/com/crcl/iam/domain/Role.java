package com.crcl.iam.domain;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table("roles")
@Data
@NoArgsConstructor
public class Role {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private UUID id = UUID.randomUUID();

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 1, ordering = Ordering.DESCENDING)
    private UUID timeUUID = Uuids.timeBased();
    @Column("enabled")
    @CassandraType(type = CassandraType.Name.BOOLEAN)
    private boolean enabled = true;

    @Column("name")
    @CassandraType(type = CassandraType.Name.TEXT)

    private String name;

    @CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.TEXT)
    @Column("permissions")
    private Set<Permission> permissions = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }
}
