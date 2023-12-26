package com.crcl.iam.domain;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("permissions")
@Data
@NoArgsConstructor
public class Permission {

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

    public Permission(String name) {
        this.name = name;
    }
}
