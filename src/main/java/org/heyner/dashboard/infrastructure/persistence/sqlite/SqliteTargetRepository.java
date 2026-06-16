package org.heyner.dashboard.infrastructure.persistence.sqlite;

import org.heyner.dashboard.domain.model.MonitoredTarget;
import org.heyner.dashboard.domain.port.out.TargetRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implémentation SQLite du repository des cibles surveillées.
 */
@Repository
public class SqliteTargetRepository implements TargetRepository {

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
        throw new UnsupportedOperationException("À implémenter");
    }

    @Override
    public void deleteById(UUID id) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
