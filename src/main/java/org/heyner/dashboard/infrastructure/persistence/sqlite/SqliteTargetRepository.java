package org.heyner.dashboard.infrastructure.persistence.sqlite;

import org.heyner.dashboard.domain.model.MonitoredTarget;
import org.heyner.dashboard.domain.model.TargetType;
import org.heyner.dashboard.domain.port.out.TargetRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implémentation SQLite du repository des cibles surveillées.
 */
@Repository
public class SqliteTargetRepository implements TargetRepository {

    private final JdbcClient jdbcClient;

    public SqliteTargetRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public MonitoredTarget save(MonitoredTarget target) {
        throw new UnsupportedOperationException("À implémenter");
    }

    @Override
    public Optional<MonitoredTarget> findById(UUID id) {
        throw new UnsupportedOperationException("À implémenter");
    }

    @Override
    public List<MonitoredTarget> findAllOrdered() {
        return jdbcClient.sql("""
                SELECT id, type, display_name, path, display_order, enabled, created_at, updated_at
                FROM monitored_target
                ORDER BY display_order ASC, created_at ASC
                """)
            .query((rs, rowNum) -> mapRow(rs))
            .list();
    }

    @Override
    public void deleteById(UUID id) {
        throw new UnsupportedOperationException("À implémenter");
    }

    private MonitoredTarget mapRow(ResultSet rs) throws SQLException {
        return new MonitoredTarget(
            UUID.fromString(rs.getString("id")),
            TargetType.valueOf(rs.getString("type")),
            rs.getString("display_name"),
            rs.getString("path"),
            rs.getInt("display_order"),
            rs.getBoolean("enabled"),
            rs.getTimestamp("created_at").toInstant(),
            rs.getTimestamp("updated_at").toInstant()
        );
    }
}
