package org.heyner.dashboard.infrastructure.persistence.sqlite;

import org.heyner.dashboard.domain.model.MonitoredTarget;
import org.heyner.dashboard.domain.model.TargetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SqliteTargetRepositoryTest {

    private SqliteTargetRepository repository;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        DataSource dataSource = new DriverManagerDataSource(
            "jdbc:h2:mem:target_repo_" + UUID.randomUUID() + ";DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
            "sa",
            ""
        );

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.repository = new SqliteTargetRepository(JdbcClient.create(dataSource));

        jdbcTemplate.execute("""
            CREATE TABLE monitored_target (
                id VARCHAR(36) PRIMARY KEY,
                type VARCHAR(30) NOT NULL,
                display_name VARCHAR(255) NOT NULL,
                path VARCHAR(1024) NOT NULL UNIQUE,
                display_order INTEGER NOT NULL,
                enabled BOOLEAN NOT NULL,
                created_at TIMESTAMP NOT NULL,
                updated_at TIMESTAMP NOT NULL
            )
            """);
    }

    @Test
    void findAllOrderedReturnsEmptyListWhenNoRowExists() {
        List<MonitoredTarget> targets = repository.findAllOrdered();

        assertTrue(targets.isEmpty());
    }

    @Test
    void findAllOrderedReturnsTargetsSortedByDisplayOrder() {
        insertTarget(
            "11111111-1111-1111-1111-111111111111",
            TargetType.WINDOWS_FOLDER,
            "Target C",
            "C:/temp/c",
            3,
            true,
            Instant.parse("2026-01-03T10:00:00Z")
        );
        insertTarget(
            "22222222-2222-2222-2222-222222222222",
            TargetType.PST_ARCHIVE,
            "Target A",
            "C:/temp/a.pst",
            1,
            false,
            Instant.parse("2026-01-01T10:00:00Z")
        );
        insertTarget(
            "33333333-3333-3333-3333-333333333333",
            TargetType.WINDOWS_FOLDER,
            "Target B",
            "C:/temp/b",
            2,
            true,
            Instant.parse("2026-01-02T10:00:00Z")
        );

        List<MonitoredTarget> targets = repository.findAllOrdered();

        assertEquals(3, targets.size());
        assertEquals("22222222-2222-2222-2222-222222222222", targets.get(0).getId().toString());
        assertEquals("33333333-3333-3333-3333-333333333333", targets.get(1).getId().toString());
        assertEquals("11111111-1111-1111-1111-111111111111", targets.get(2).getId().toString());
    }

    @Test
    void findAllOrderedMapsAllFields() {
        Instant createdAt = Instant.parse("2026-02-10T15:30:00Z");
        insertTarget(
            "44444444-4444-4444-4444-444444444444",
            TargetType.PST_ARCHIVE,
            "Archive Mail",
            "C:/mail/archive.pst",
            5,
            false,
            createdAt
        );

        MonitoredTarget target = repository.findAllOrdered().getFirst();

        assertEquals(UUID.fromString("44444444-4444-4444-4444-444444444444"), target.getId());
        assertEquals(TargetType.PST_ARCHIVE, target.getType());
        assertEquals("Archive Mail", target.getDisplayName());
        assertEquals("C:/mail/archive.pst", target.getPath());
        assertEquals(5, target.getDisplayOrder());
        assertFalse(target.isEnabled());
        assertEquals(createdAt, target.getCreatedAt());
        assertEquals(createdAt, target.getUpdatedAt());
    }

    private void insertTarget(String id,
                              TargetType type,
                              String displayName,
                              String path,
                              int displayOrder,
                              boolean enabled,
                              Instant timestamp) {
        jdbcTemplate.update(
            """
            INSERT INTO monitored_target (
                id, type, display_name, path, display_order, enabled, created_at, updated_at
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """,
            id,
            type.name(),
            displayName,
            path,
            displayOrder,
            enabled,
            Timestamp.from(timestamp),
            Timestamp.from(timestamp)
        );
    }
}

