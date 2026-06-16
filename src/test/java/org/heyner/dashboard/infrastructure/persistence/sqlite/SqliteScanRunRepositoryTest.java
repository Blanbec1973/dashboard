package org.heyner.dashboard.infrastructure.persistence.sqlite;

import org.heyner.dashboard.domain.model.ScanRun;
import org.heyner.dashboard.domain.model.ScanStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SqliteScanRunRepositoryTest {

    private SqliteScanRunRepository repository;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        DataSource dataSource = new DriverManagerDataSource(
            "jdbc:h2:mem:scan_run_repo_" + UUID.randomUUID() + ";DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
            "sa",
            ""
        );

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.repository = new SqliteScanRunRepository(JdbcClient.create(dataSource));

        jdbcTemplate.execute("""
            CREATE TABLE scan_run (
                id VARCHAR(36) PRIMARY KEY,
                started_at TIMESTAMP NOT NULL,
                finished_at TIMESTAMP NULL,
                status VARCHAR(20) NOT NULL,
                error_message VARCHAR(2000) NULL
            )
            """);
    }

    @Test
    void saveInsertsNewScanRun() {
        UUID id = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
        Instant startedAt = Instant.parse("2026-06-16T08:00:00Z");
        ScanRun scanRun = new ScanRun(id, startedAt, null, ScanStatus.SUCCESS, null);

        ScanRun saved = repository.save(scanRun);

        assertEquals(scanRun, saved);
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM scan_run WHERE id = ?", Integer.class, id.toString());
        assertEquals(1, count);
        assertEquals("SUCCESS", jdbcTemplate.queryForObject("SELECT status FROM scan_run WHERE id = ?", String.class, id.toString()));
        assertNull(jdbcTemplate.queryForObject("SELECT finished_at FROM scan_run WHERE id = ?", Timestamp.class, id.toString()));
    }

    @Test
    void saveUpdatesExistingScanRun() {
        UUID id = UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb");
        Instant startedAt = Instant.parse("2026-06-16T09:00:00Z");
        jdbcTemplate.update(
            "INSERT INTO scan_run (id, started_at, finished_at, status, error_message) VALUES (?, ?, ?, ?, ?)",
            id.toString(),
            Timestamp.from(startedAt),
            null,
            "SUCCESS",
            null
        );

        Instant finishedAt = Instant.parse("2026-06-16T09:05:00Z");
        ScanRun updated = new ScanRun(id, startedAt, finishedAt, ScanStatus.ERROR, "timeout");

        repository.save(updated);

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM scan_run WHERE id = ?", Integer.class, id.toString());
        assertEquals(1, count);
        assertEquals("ERROR", jdbcTemplate.queryForObject("SELECT status FROM scan_run WHERE id = ?", String.class, id.toString()));
        assertEquals("timeout", jdbcTemplate.queryForObject("SELECT error_message FROM scan_run WHERE id = ?", String.class, id.toString()));
        assertEquals(Timestamp.from(finishedAt), jdbcTemplate.queryForObject("SELECT finished_at FROM scan_run WHERE id = ?", Timestamp.class, id.toString()));
    }
}

