package org.heyner.dashboard.infrastructure.persistence.sqlite;

import org.heyner.dashboard.domain.model.TargetSnapshot;
import org.heyner.dashboard.domain.port.out.SnapshotRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implémentation SQLite du repository des snapshots.
 */
@Repository
public class SqliteSnapshotRepository implements SnapshotRepository {

    @Override
    public TargetSnapshot save(TargetSnapshot snapshot) {
        throw new UnsupportedOperationException("À implémenter");
    }

    @Override
    public Optional<TargetSnapshot> findLatestByTargetId(UUID targetId) {
        throw new UnsupportedOperationException("À implémenter");
    }

    @Override
    public List<TargetSnapshot> findLatestSnapshotsOrdered() {
        throw new UnsupportedOperationException("À implémenter");
    }
}
