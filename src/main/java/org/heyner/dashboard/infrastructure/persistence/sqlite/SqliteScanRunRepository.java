package org.heyner.dashboard.infrastructure.persistence.sqlite;

import org.heyner.dashboard.domain.model.ScanRun;
import org.heyner.dashboard.domain.port.out.ScanRunRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * Implémentation SQLite du repository des exécutions de scan.
 */
@Repository
public class SqliteScanRunRepository implements ScanRunRepository {

    private final JdbcClient jdbcClient;

    public SqliteScanRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public ScanRun save(ScanRun scanRun) {
        int updatedRows = jdbcClient.sql("""
                UPDATE scan_run
                SET started_at = ?,
                    finished_at = ?,
                    status = ?,
                    error_message = ?
                WHERE id = ?
                """)
            .params(
                Timestamp.from(scanRun.getStartedAt()),
                scanRun.getFinishedAt() == null ? null : Timestamp.from(scanRun.getFinishedAt()),
                scanRun.getStatus().name(),
                scanRun.getErrorMessage(),
                scanRun.getId().toString()
            )
            .update();

        if (updatedRows == 0) {
            jdbcClient.sql("""
                    INSERT INTO scan_run (id, started_at, finished_at, status, error_message)
                    VALUES (?, ?, ?, ?, ?)
                    """)
                .params(
                    scanRun.getId().toString(),
                    Timestamp.from(scanRun.getStartedAt()),
                    scanRun.getFinishedAt() == null ? null : Timestamp.from(scanRun.getFinishedAt()),
                    scanRun.getStatus().name(),
                    scanRun.getErrorMessage()
                )
                .update();
        }

        return scanRun;
    }
}
